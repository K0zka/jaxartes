package com.github.K0zka.jaxartes.resolvers;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.PathParam;

public class PathParameterResolver implements ParameterResolver<PathParam> {
    @Override
    public Class<? extends Annotation> resolves() {
        return PathParam.class;
    }

    @Override
	public Object resolve(HttpServletRequest request,
			HttpServletResponse response, Method method,
			Annotation[] annotations, Annotation annotation,
			Class<?> expectedClass) {
        final String path = request.getRequestURI();
        final String paramToResolve = ((PathParam)annotation).value();
        return null;
    }

}
