package cz.muni.fi.PA165.flight.web.config;

import cz.muni.fi.PA165.flight.web.conversion.StringToAirportConverter;
import cz.muni.fi.PA165.flight.web.conversion.StringToPlaneConverter;
import cz.muni.fi.PA165.flight.web.conversion.StringToStewardConverter;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Locale;

/**
 * User: PC
 * Date: 22. 11. 2014
 * Time: 17:01
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "cz.muni.fi.PA165.flight")
@ImportResource({"classpath:application-context.xml"})
public class MySpringMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
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
    public StringToPlaneConverter getPlaneConverter()
    {
        return new StringToPlaneConverter();
    }

    @Bean
    public StringToAirportConverter getAirportConverter()
    {
        return new StringToAirportConverter();
    }


    @Bean
    public StringToStewardConverter getStringToStewardConverter(){
        return new StringToStewardConverter();
    }


    @Override
    public void addFormatters(FormatterRegistry formatterRegistry)
    {
        formatterRegistry.addConverter(getPlaneConverter());
        formatterRegistry.addConverter(getAirportConverter());
        formatterRegistry.addConverter(getStringToStewardConverter());
    }
}
