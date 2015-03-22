package com.github.K0zka.jaxartes.resolvers;

import com.github.K0zka.jaxartes.injectables.AsyncResponseImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.container.Suspended;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Resolves the suspended query parameter
 */
public class AsyncResponseParameterResolver implements
		ParameterResolver<Suspended> {
	@Override
	public Class<? extends Annotation> resolves() {
		return Suspended.class;
	}

	@Override
	public Object resolve(HttpServletRequest request,
			HttpServletResponse response, Method method,
			Annotation[] annotations, Annotation annotation,
			Class<?> expectedClass) {
		request.startAsync();
		return new AsyncResponseImpl(request, response);
	}

}
