package com.github.K0zka.jaxartes.injectables;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

@RunWith(MockitoJUnitRunner.class)
public class UriInfoImplTest {
    @Mock
    HttpServletRequest request;

    @Test
    public void getRequestUri() {
        Mockito.when(request.getRequestURI()).thenReturn("/resource");
        Assert.assertEquals("/resource", new UriInfoImpl(request).getRequestUri().getPath());
    }

}
