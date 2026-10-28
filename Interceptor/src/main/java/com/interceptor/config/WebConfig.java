package com.interceptor.config;

import com.interceptor.interceptor.LoggingInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    public LoggingInterceptor loggingInterceptor;

    public WebConfig(LoggingInterceptor interceptor){
        this.loggingInterceptor = interceptor;

    }
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(loggingInterceptor);
    }

}
