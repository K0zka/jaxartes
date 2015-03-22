package com.github.K0zka.jaxartes.injectables;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.TimeoutHandler;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class AsyncResponseImpl implements AsyncResponse {

    final HttpServletRequest request;
    final HttpServletResponse response;

    public AsyncResponseImpl(final HttpServletRequest request, final HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    @Override
    public boolean resume(Object response) {
        try {
            this.response.setContentType(MediaType.APPLICATION_JSON);
            new ObjectMapper().writeValue(this.response.getWriter(), response);
            request.getAsyncContext().complete();
            return true;
        } catch (IOException ioe) {
            return false;
        }
    }

    @Override
    public boolean resume(Throwable response) {
        return true;
    }

    @Override
    public boolean cancel() {
        try {
            final AsyncContext asyncContext = request.getAsyncContext();
            response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            asyncContext.complete();
            return true;
        } catch (IllegalStateException ise) {
            return false;
        }
    }

    @Override
    public boolean cancel(int retryAfter) {
        return false;
    }

    @Override
    public boolean cancel(Date retryAfter) {
        return false;
    }

    @Override
    public boolean isSuspended() {
        return request.isAsyncStarted();
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public boolean setTimeout(long time, TimeUnit unit) {
        request.getAsyncContext().setTimeout(TimeUnit.MILLISECONDS.convert(time, unit));
        return true;
    }

    @Override
    public void setTimeoutHandler(TimeoutHandler handler) {

    }

    @Override
    public Collection<Class<?>> register(Class<?> callback) {
        return null;
    }

    @Override
    public Map<Class<?>, Collection<Class<?>>> register(Class<?> callback, Class<?>... callbacks) {
        return null;
    }

    @Override
    public Collection<Class<?>> register(Object callback) {
        return null;
    }

    @Override
    public Map<Class<?>, Collection<Class<?>>> register(Object callback, Object... callbacks) {
        return null;
    }
}
