package com.github.K0zka.jaxartes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.K0zka.jaxartes.resolvers.ParameterResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Objects;

public class ServiceMethodImpl implements ServiceMethod {

    private final Method method;
    private final Object service;
    private final List<ParameterResolver<?>> resolvers;
    private final ObjectMapper objectMapper;

    public ServiceMethodImpl(final Method method,
                             final Object service,
                             final List<ParameterResolver<?>> resolvers,
                             final ObjectMapper objectMapper) {
        this.method = method;
        this.service = service;
        this.resolvers = resolvers;
        this.objectMapper = objectMapper;
    }

	@Override
    public void execute(final HttpServletRequest request, final HttpServletResponse response) {
        final Object[] params = new Object[resolvers.size()];
        for(int i = 0; i < resolvers.size(); i++) {
            final ParameterResolver<?> resolver = resolvers.get(i);

            final Class<? extends Annotation> resolvedAnnotation = resolver.resolves();
            Annotation[] parameterAnnotations = method.getParameterAnnotations()[i];
            Annotation annotation = getAnnotation(resolvedAnnotation, parameterAnnotations);
			params[i] = resolver.resolve(
                    request,
                    response,
                    method,
                    method.getDeclaredAnnotations(),
                    annotation,
                    method.getReturnType());
        }

        try{
            Object result = method.invoke(service, params);
            response.setContentType(MediaType.APPLICATION_JSON);
            objectMapper.writer().writeValue(response.getOutputStream(), result);
        } catch (IOException | IllegalAccessException | InvocationTargetException ioe) {

        }
    }

    @SuppressWarnings("unchecked")
	private static <T extends Annotation> T getAnnotation(Class<? extends T> resolvedAnnotation, Annotation[] parameterAnnotations) {
        if(resolvedAnnotation != null) {
            for(Annotation annotation : parameterAnnotations) {
                if(resolvedAnnotation.isInstance(annotation) ) {
                    return (T)annotation;
                }
            }
        }
        return null;
    }

    @Override
    public boolean matches(final HttpServletRequest request) {
        //TODO: this is not correct, ignores path parameters
        Path path = method.getDeclaringClass().getAnnotation(Path.class);
        if(Objects.equals(path.value(), request.getRequestURI())) {
            return true;
        }
        return false;
    }
}
