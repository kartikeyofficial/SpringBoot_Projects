package com.filterAuthentication.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

//@Component
public class LoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain)
            throws IOException, ServletException {

        long startTime = System.currentTimeMillis();

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestId = UUID.randomUUID().toString();
        httpResponse.setHeader("X-Request-ID: ",requestId);
        // Request Log
        System.out.println("Incoming Request: "+httpRequest.getMethod()+" "+httpRequest.getRequestURI());
        try {
            filterChain.doFilter(request,response);
        }
        finally {

            long duration = System.currentTimeMillis() - startTime;

            // Response Log
            System.out.println("Response Status: "+httpResponse.getStatus());
            System.out.println("API Response Time: "+duration);

        }


    }
}
