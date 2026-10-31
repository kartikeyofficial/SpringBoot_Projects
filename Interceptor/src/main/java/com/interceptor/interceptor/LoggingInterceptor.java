package com.interceptor.interceptor;

import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

//@Component
public class LoggingInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        HandlerMethod method = (HandlerMethod) handler;

        String controllerName = method.getBeanType().getName();
        String methodName = method.getMethod().getName();
        System.out.println("PreHandler called");
        System.out.println("Controller Name: "+controllerName);
        System.out.println("Method Name: "+methodName);
        System.out.println("Query String: "+request.getQueryString());
        System.out.println("client Ip: "+request.getRemoteAddr());
        System.out.println("Token Header: "+request.getHeader("token"));

        if (handler instanceof HandlerMethod handlerMethod){
            System.out.println("controller: "+handlerMethod.getBeanType().getName());
            System.out.println("Controller Method: "+handlerMethod.getMethod().getName());
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {

        System.out.println("PostHandle Called");
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {

        System.out.println("Response Status: "+response.getStatus());
    }
}
