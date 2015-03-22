package com.github.K0zka.jaxartes.resolvers;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Providers;

import com.github.K0zka.jaxartes.injectables.ProvidersImpl;

public class ContextParameterResolver implements ParameterResolver<Context> {

    @SuppressWarnings("serial")
	private final static Map<Class<?>, SubResolver> typeResolvers = Collections.unmodifiableMap(new HashMap<Class<?>, SubResolver>() {
        {
            put(Providers.class, new SubResolver() {
                @Override
                public Object resolve(HttpServletRequest request, HttpServletResponse response, Method method, Annotation[] annotations, Context annotation) {
                    return new ProvidersImpl();
                }
            });
        }
    });

    @Override
    public Class<? extends Annotation> resolves() {
        return Context.class;
    }

	@Override
	public Object resolve(HttpServletRequest request,
			HttpServletResponse response, Method method,
			Annotation[] annotations, Annotation annotation,
			Class<?> expectedClass) {
		return typeResolvers.get(expectedClass).resolve(request, response,
				method, annotations, (Context)annotation);
	}

    public static interface SubResolver {
        Object resolve(HttpServletRequest request,
                       HttpServletResponse response,
                       Method method,
                       Annotation[] annotations,
                       Context annotation);
    }

}
