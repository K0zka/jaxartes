package com.github.K0zka.jaxartes.spring;

import com.github.K0zka.jaxartes.ServiceSwitch;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@RunWith(MockitoJUnitRunner.class)
public class SpringJaxrsServletTest {

    @Mock
    ServletConfig config;

    @Mock
    WebApplicationContext appContext;

    @Mock
    ServletContext context;

    @Mock
    ServiceSwitch serviceSwitch;

    @Before
    public void setup() {
        Mockito.when(config.getServletContext()).thenReturn(context);
        Mockito.when(context.getAttribute(Mockito.eq(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE))).thenReturn(appContext);
        Mockito.when(appContext.getBean(Mockito.eq("jaxrs-micro"))).thenReturn(serviceSwitch);
    }

    @Test
    public void getServiceSwitch() throws ServletException {
        SpringJaxrsServlet servlet = new SpringJaxrsServlet();
        servlet.init(config);
        Assert.assertEquals(serviceSwitch, servlet.getServiceSwitch());
    }

}
