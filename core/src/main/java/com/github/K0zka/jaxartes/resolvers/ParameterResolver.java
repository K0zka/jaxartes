package com.github.K0zka.jaxartes.resolvers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Resolves a parameter from the http query.
 * Instances of ParameterResolver are reused and therefore need to be stateless.
 * @param <T>   the annotation type that the resolver handles
 */
public interface ParameterResolver<T extends Annotation> {
    /**
     * Get the annotation type that this class works with.
     * @return
     */
    Class<? extends Annotation> resolves();

    /**
     * Resolve the parameter.
     * @param request   the http request
     * @param response  the http response
     * @param method    the method that should be invoked
     * @param annotations   the annotations on the method
     * @param annotation the annotation on the parameter
     * @param expectedClass the expected type of the returned object
     * @return  the value or null
     */
    Object resolve(
            HttpServletRequest request,
            HttpServletResponse response,
            Method method,
            Annotation[] annotations,
            Annotation annotation, Class<?> expectedClass);
}
