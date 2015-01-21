package cz.muni.fi.PA165.flight.web.config;

import cz.muni.fi.PA165.flight.web.conversion.*;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * User: PC
 * Date: 22. 11. 2014
 * Time: 17:01
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"cz.muni.fi.PA165.flight", "org.springframework.security.samples.mvc"})
@ImportResource({"classpath:application-context.xml"})
@Import({SecurityConfig.class})
public class MySpringMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
    }


    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("Texts");
        messageSource.setFallbackToSystemLocale(true);
        return messageSource;
    }

    @Bean
    public Validator validator() {
        return new LocalValidatorFactoryBean();
    }


    @Bean
    public StringToPlaneConverter getPlaneConverter() {
        return new StringToPlaneConverter();
    }

    @Bean
    public StringToAirportConverter getAirportConverter() {
        return new StringToAirportConverter();
    }


    @Bean
    public StringToStewardConverter getStringToStewardConverter() {
        return new StringToStewardConverter();
    }

    @Bean
    public StringToUserConverter getStringToUserConverter() {
        return new StringToUserConverter();
    }

    @Bean
    public StringToUserRoleConverter getStringToUserRoleConverter() {
        return new StringToUserRoleConverter();
    }

    @Override
    public void addFormatters(FormatterRegistry formatterRegistry) {
        formatterRegistry.addConverter(getPlaneConverter());
        formatterRegistry.addConverter(getAirportConverter());
        formatterRegistry.addConverter(getStringToStewardConverter());
        formatterRegistry.addConverter(getStringToUserConverter());
        formatterRegistry.addConverter(getStringToUserRoleConverter());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RequestInterceptor());
    }


}
