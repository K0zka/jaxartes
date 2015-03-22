package com.github.K0zka.jaxartes.resolvers;

import com.github.K0zka.jaxartes.mapping.Mappers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.QueryParam;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class QueryParameterResolver implements ParameterResolver<QueryParam> {
	@Override
	public Class<? extends Annotation> resolves() {
		return QueryParam.class;
	}

	@Override
	public Object resolve(HttpServletRequest request,
			HttpServletResponse response, Method method,
			Annotation[] annotations, Annotation annotation,
			Class<?> expectedClass) {
		return Mappers.mapTo(expectedClass,
				request.getParameter(((QueryParam) annotation).value()));
	}

}
