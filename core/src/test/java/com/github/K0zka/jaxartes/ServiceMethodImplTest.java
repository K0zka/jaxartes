package com.github.K0zka.jaxartes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.K0zka.jaxartes.resolvers.ParameterResolver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
public class ServiceMethodImplTest {
    @Mock
    ParameterResolver<?> resolver1;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    ServletOutputStream outputStream;

    @Path("/greet")
    private interface Service {

        @GET
        @Path("/hello")
        public String sayHello(@QueryParam("name") String name);
    }

    @Mock
    Service instance;

	@Test
    public void execute() throws Exception {
        Mockito.when(response.getOutputStream()).thenReturn(outputStream);

        Mockito.when(resolver1.resolves()).thenReturn((Class)QueryParam.class);
        Mockito.when(resolver1.resolve(
                Matchers.any(HttpServletRequest.class),
                Matchers.any(HttpServletResponse.class),
                Matchers.any(Method.class),
                Matchers.any(Annotation[].class),
                Matchers.any(Annotation.class),
                Matchers.any(Class.class))).thenReturn("world");

        ServiceMethodImpl serviceMethod = new ServiceMethodImpl(
                Service.class.getMethod("sayHello", String.class),
                instance,
                Arrays.<ParameterResolver<?>>asList(resolver1),
                new ObjectMapper());
        serviceMethod.execute(request, response);

        Mockito.verify(instance).sayHello(Matchers.eq("world"));
    }
}
