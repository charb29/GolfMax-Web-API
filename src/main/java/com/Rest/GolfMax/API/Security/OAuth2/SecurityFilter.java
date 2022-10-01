package com.Rest.GolfMax.API.Security.OAuth2;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Component
@WebFilter("/*")
public class SecurityFilter implements Filter {

    private static final Set<String> ALLOWED_PATHS = Set
            .of("/user/signup/verification", "/player-statistics", "/scores", "/courses", "/user/sign-in");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String urlPath = ((HttpServletRequest) request).getRequestURI().substring(((HttpServletRequest) request)
                        .getContextPath()
                        .length()).replaceAll("/+$", "");
        boolean allowedPath = ALLOWED_PATHS.contains(urlPath);

        if ("GET".equalsIgnoreCase(httpServletRequest.getMethod())) {
            Cookie cookie = new Cookie("XSRF-TOKEN", UUID.randomUUID().toString());
            cookie.setHttpOnly(false);
            httpServletResponse.addCookie(cookie);
        }
        else if ("POST".equalsIgnoreCase(httpServletRequest.getMethod())) {
            Cookie cookie = WebUtils.getCookie(httpServletRequest, "XSRF-TOKEN");
            String csrfHeader = httpServletRequest.getHeader("X-XSRF-TOKEN");

            if (allowedPath) {
                chain.doFilter(request, response);
                return;
            }
            if (cookie != null && !Objects.equals(csrfHeader, cookie.getValue())) {
                httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
                return;
            }
        }

        chain.doFilter(request, response);
    }
}