package com.github.K0zka.jaxartes.resolvers;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.container.Suspended;

@RunWith(MockitoJUnitRunner.class)
public class AsyncResponseParameterResolverTest {
    @Mock
    HttpServletResponse response;
    @Mock
    HttpServletRequest request;

    @Test
    public void resolve() {
        Assert.assertNotNull(new AsyncResponseParameterResolver().resolve(
                request,
                response,
                null,
                null,
                Mockito.mock(Suspended.class),
                null));
        Mockito.verify(request).startAsync();
    }
}
