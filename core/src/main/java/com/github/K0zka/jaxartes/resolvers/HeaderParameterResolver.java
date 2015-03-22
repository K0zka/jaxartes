package com.github.K0zka.jaxartes.resolvers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.HeaderParam;

import com.github.K0zka.jaxartes.mapping.Mappers;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class HeaderParameterResolver implements ParameterResolver<HeaderParam> {
	@Override
	public Class<? extends Annotation> resolves() {
		return HeaderParam.class;
	}

	@Override
	public Object resolve(HttpServletRequest request,
			HttpServletResponse response, Method method,
			Annotation[] annotations, Annotation annotation,
			Class<?> expectedClass) {
		return Mappers.mapTo(expectedClass,
				request.getHeader(((HeaderParam) annotation).value()));
	}
}
