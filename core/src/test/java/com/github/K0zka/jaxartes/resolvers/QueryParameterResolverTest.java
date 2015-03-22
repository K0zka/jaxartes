package com.github.K0zka.jaxartes.resolvers;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.QueryParam;

@RunWith(MockitoJUnitRunner.class)
public class QueryParameterResolverTest {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    QueryParam param;

    @Test
    public void resolve() {
        Mockito.when(param.value()).thenReturn("foo");
        Mockito.when(request.getParameter(Matchers.eq("foo"))).thenReturn("bar");
        Assert.assertEquals("bar", new QueryParameterResolver().resolve(
                request,
                response,
                null,
                null,
                param,
                String.class));

        Mockito.verify(request).getParameter(Matchers.eq("foo"));
        Mockito.verify(param).value();

    }
}
