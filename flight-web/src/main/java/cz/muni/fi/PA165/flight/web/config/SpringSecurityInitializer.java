package cz.muni.fi.PA165.flight.web.config;


import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * User: PC
 * Date: 17. 1. 2015
 * Time: 21:10
 */
public class SpringSecurityInitializer extends AbstractSecurityWebApplicationInitializer {
    protected String getDispatcherWebApplicationContextSuffix() {
        return org.springframework.web.servlet.support.AbstractDispatcherServletInitializer.DEFAULT_SERVLET_NAME;
    }

}


