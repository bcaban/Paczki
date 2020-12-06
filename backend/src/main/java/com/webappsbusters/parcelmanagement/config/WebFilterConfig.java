package com.webappsbusters.parcelmanagement.config;

import com.webappsbusters.parcelmanagement.controller.log.interceptor.LogInterceptor;
import com.webappsbusters.parcelmanagement.controller.log.service.LoggingService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebFilterConfig implements WebMvcConfigurer {
    private final LoggingService loggingService;

    public WebFilterConfig(LoggingService loggingService) {
        this.loggingService = loggingService;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor(loggingService));
    }
}
