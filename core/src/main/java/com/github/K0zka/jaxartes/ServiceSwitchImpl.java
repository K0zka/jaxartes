package com.github.K0zka.jaxartes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.K0zka.jaxartes.resolvers.ParameterResolver;
import com.github.K0zka.jaxartes.resolvers.QueryParameterResolver;

import javax.servlet.ServletConfig;
import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * The default implementation of {@link ServiceSwitch}.
 */
public class ServiceSwitchImpl implements ServiceSwitch {

    final ObjectMapper objectMapper;

    /**
     * Create an instance with the list of service objects.
     * @param services
     */
    public ServiceSwitchImpl(final List<Object> services) {
        objectMapper = new ObjectMapper();
        final List<ServiceMethod> serviceMethods = new ArrayList<>();
        for(final Object service : services) {
            serviceMethods.addAll( discover(service.getClass(), service) );
        }
        this.services = Collections.unmodifiableList(serviceMethods);
    }

    final List<ServiceMethod> discover(Class<?> serviceType, Object service) {
        final List<ServiceMethod> serviceMethods = new ArrayList<>();
        for(Method method : serviceType.getMethods()) {
            if(Modifier.isPublic(method.getModifiers())) {
                ServiceMethod serviceMethod = new ServiceMethodImpl(
                        method,
                        service,
                        Arrays.<ParameterResolver<?>>asList(new QueryParameterResolver()),
                        objectMapper);
                serviceMethods.add(serviceMethod);
            }
        }
        return serviceMethods;
    }



    final List<ServiceMethod> services;

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response, ServletConfig context) {
        ServletRegistration registration =  context.getServletContext().getServletRegistration(context.getServletName());
        registration.getMappings();
        ServiceMethod method = lookUp(request);
        if(method == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } else {
            method.execute(request, response);
        }
    }

    private ServiceMethod lookUp(final HttpServletRequest request) {
        //TODO: find a more efficient lookup method
        for(ServiceMethod method : services) {
            if(method.matches(request)) {
                return method;
            }
        }
        return null;
    }
}
