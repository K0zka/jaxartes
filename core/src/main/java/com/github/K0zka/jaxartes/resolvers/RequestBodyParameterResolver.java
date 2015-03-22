package com.github.K0zka.jaxartes.resolvers;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@SuppressWarnings("rawtypes")
public class RequestBodyParameterResolver implements ParameterResolver {

	final ObjectMapper objectMapper;

	public RequestBodyParameterResolver(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Override
	public Class<? extends Annotation> resolves() {
		return null;
	}

	@Override
	public Object resolve(HttpServletRequest request,
			HttpServletResponse response, Method method,
			Annotation[] annotations, Annotation annotation, @SuppressWarnings("rawtypes") Class expectedClass) {
		try {
			return objectMapper.reader().readValue(
					new JsonFactory().createParser(request.getInputStream()),
					TypeFactory.defaultInstance().uncheckedSimpleType(
							expectedClass));
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		}
	}

}
