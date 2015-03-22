package com.github.K0zka.jaxartes.injectables;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

@RunWith(MockitoJUnitRunner.class)
public class AsyncResponseImplTest {
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    AsyncContext asyncContext;

    AsyncResponseImpl asyncResponse;

    PrintWriter printWriter;

    StringWriter stringWriter;

    @Before
    public void setup() throws IOException {
        stringWriter = new StringWriter();
        printWriter = new PrintWriter(stringWriter);
        Mockito.when(request.getAsyncContext()).thenReturn(asyncContext);
        Mockito.when(response.getWriter()).thenReturn(printWriter);
        asyncResponse = new AsyncResponseImpl(request, response);
    }

    @Test
    public void resumeWithResponse() throws IOException {
        asyncResponse.resume("PASSED");
        Mockito.verify(response).getWriter();
        Mockito.verify(response).setContentType(Matchers.eq(MediaType.APPLICATION_JSON));
        Mockito.verify(asyncContext).complete();
        Assert.assertEquals("\"PASSED\"", stringWriter.toString());
    }

    @Test
    public void cancel() {
        asyncResponse.cancel();
        Mockito.verify(response).setStatus(Matchers.eq(HttpServletResponse.SC_SERVICE_UNAVAILABLE));
        Mockito.verify(asyncContext).complete();
    }

    @Test
    public void cancelFail() {
        Mockito.when(request.getAsyncContext()).thenThrow(new IllegalStateException("async context not started"));
        asyncResponse.cancel();
        Mockito.verify(response, Mockito.never()).setStatus(Matchers.eq(HttpServletResponse.SC_SERVICE_UNAVAILABLE));
        Mockito.verify(asyncContext, Mockito.never()).complete();
    }

}
