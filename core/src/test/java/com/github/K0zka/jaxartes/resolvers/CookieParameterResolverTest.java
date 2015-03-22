package com.github.K0zka.jaxartes.resolvers;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.CookieParam;

@RunWith(MockitoJUnitRunner.class)
public class CookieParameterResolverTest {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    CookieParam param;
    @Mock
    Cookie cookie1;
    @Mock
    Cookie cookie2;


    @Test
    public void resolve() {
        Mockito.when(param.value()).thenReturn("foo");
        Mockito.when(cookie1.getName()).thenReturn("bar");
        Mockito.when(cookie2.getName()).thenReturn("foo");
        Mockito.when(cookie2.getValue()).thenReturn("pass");
        Mockito.when(request.getCookies()).thenReturn(new Cookie[]{cookie1, cookie2});

        Assert.assertEquals("pass", new CookieParameterResolver().resolve(request, response, null, null, param, null));

        Mockito.verify(request).getCookies();
    }
}
