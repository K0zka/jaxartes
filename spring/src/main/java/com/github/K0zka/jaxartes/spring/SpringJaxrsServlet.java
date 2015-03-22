package com.github.K0zka.jaxartes.spring;

import com.github.K0zka.jaxartes.AbstractJaxrsServlet;
import com.github.K0zka.jaxartes.ServiceSwitch;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SpringJaxrsServlet extends AbstractJaxrsServlet {
    @Override
    public ServiceSwitch getServiceSwitch() {
        return (ServiceSwitch) WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext()).getBean("jaxrs-micro");
    }
}
