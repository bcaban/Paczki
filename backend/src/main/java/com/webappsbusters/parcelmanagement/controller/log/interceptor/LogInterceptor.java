package com.webappsbusters.parcelmanagement.controller.log.interceptor;

import com.webappsbusters.parcelmanagement.controller.log.service.LoggingService;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.DispatcherType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogInterceptor implements HandlerInterceptor {
    private final LoggingService loggingService;

    public LogInterceptor(LoggingService loggingService) {
        this.loggingService = loggingService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        if (DispatcherType.REQUEST.name().equals(request.getDispatcherType().name()) && request.getMethod().equals(HttpMethod.GET.name())) {
            loggingService.logRequest(request, null);
        }

        return true;
    }
}
