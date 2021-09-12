package com.hackit.ticketbackend;

import java.io.File;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
class WebConfigurer extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String currentPath = new File(".").getAbsolutePath();
        String location = "file://" + currentPath.substring(0, currentPath.length() - 1) + "web/";
        registry.addResourceHandler("/**").addResourceLocations(location);
    }

}