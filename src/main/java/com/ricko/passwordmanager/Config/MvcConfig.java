package com.ricko.passwordmanager.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@EnableWebMvc u dont need this anotation cause Spring Boot adds it automatically when it sees spring-webmvc on the classpath
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    /*Overrides the method of the same name in WebMvcConfigurer
    * u can set controllers here instead of using @RequestMapping methods in @Controller*/
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/login").setViewName("user/login");
        registry.addViewController("/hello").setViewName("hello");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }
}
