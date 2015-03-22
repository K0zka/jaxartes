package com.github.K0zka.jaxartes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * An service method of a web service.
 */
public interface ServiceMethod {
    void execute(HttpServletRequest request, HttpServletResponse response);

    boolean matches(HttpServletRequest request);
}
