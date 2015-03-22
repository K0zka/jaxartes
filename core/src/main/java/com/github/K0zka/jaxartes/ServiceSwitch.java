package com.github.K0zka.jaxartes;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The service switch decides which service should be invoked.
 */
public interface ServiceSwitch {
    void service(HttpServletRequest request, HttpServletResponse response, ServletConfig context);
}
