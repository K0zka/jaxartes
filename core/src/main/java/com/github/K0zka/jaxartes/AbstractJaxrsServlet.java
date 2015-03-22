package com.github.K0zka.jaxartes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractJaxrsServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8265381266844774999L;

	public abstract ServiceSwitch getServiceSwitch();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServiceSwitch().service(req, resp, getServletConfig());
    }
}
