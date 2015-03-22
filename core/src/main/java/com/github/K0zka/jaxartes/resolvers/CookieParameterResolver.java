package com.github.K0zka.jaxartes.resolvers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.CookieParam;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Objects;

public class CookieParameterResolver implements ParameterResolver<CookieParam> {
	@Override
	public Class<? extends Annotation> resolves() {
		return CookieParam.class;
	}

	@Override
	public Object resolve(HttpServletRequest request,
			HttpServletResponse response, Method method,
			Annotation[] annotations, Annotation annotation,
			Class<?> expectedClass) {

		for (Cookie cookie : request.getCookies()) {
			if (Objects.equals(cookie.getName(),
					((CookieParam) annotation).value())) {
				return cookie.getValue();
			}
		}
		return null;
	}

}
