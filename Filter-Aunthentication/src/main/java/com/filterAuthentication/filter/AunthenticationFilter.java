package com.filterAuthentication.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AunthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String token = httpRequest.getHeader("token");
        String apiKey = httpRequest.getHeader("x-api-key");
        if (token==null || !token.equals("12345")){
             httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
             return;
        }
        if (apiKey == null || !apiKey.equals("secret123")){
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpResponse.setContentType("application/json");
            httpResponse.getWriter().write(
                    "{\n" +
                            "    \"message\":\"Invalid and missing API key\"\n" +
                            "}"

            );
            return;
        }
        filterChain.doFilter(request,response);
    }
}
