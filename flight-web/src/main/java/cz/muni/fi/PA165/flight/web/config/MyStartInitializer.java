package cz.muni.fi.PA165.flight.web.config;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.jsp.jstl.core.Config;

/**
 * User: PC
 * Date: 22. 11. 2014
 * Time: 17:06
 */

public class MyStartInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(MySpringMvcConfig.class);

        ContextLoaderListener contextLoaderListener = new ContextLoaderListener(ctx);
        servletContext.addListener(contextLoaderListener);


        //register Spring MVC main Dispatcher servlet
        ServletRegistration.Dynamic disp = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
        disp.setLoadOnStartup(1);
        disp.addMapping("/");

        //register filter setting utf-8 encoding on all requests
        FilterRegistration.Dynamic encoding = servletContext.addFilter("encoding", CharacterEncodingFilter.class);
        encoding.setInitParameter("encoding", "utf-8");
        encoding.addMappingForUrlPatterns(null, false, "/*");

        //register bundle also for JSTL fmt: tags which are not behind DispatcherServlet
        servletContext.setInitParameter(Config.FMT_LOCALIZATION_CONTEXT, "Texts");

        FilterRegistration.Dynamic corsFilter = servletContext.addFilter("corsFilter", CORSFilter.class);
        corsFilter.addMappingForUrlPatterns(null, false, "/*");

    }

}

