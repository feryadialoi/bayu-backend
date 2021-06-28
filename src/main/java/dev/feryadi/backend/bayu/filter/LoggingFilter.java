package dev.feryadi.backend.bayu.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Slf4j
@Component
public class LoggingFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        log.info("getMethod         : " + httpServletRequest.getMethod());
        log.info("getServletPath    : " + httpServletRequest.getServletPath());
        log.info("getPathInfo       : " + httpServletRequest.getPathInfo());
        log.info("getPathTranslated : " + httpServletRequest.getPathTranslated());
        log.info("getQueryString    : " + httpServletRequest.getQueryString());
        log.info("getRemoteUser     : " + httpServletRequest.getRemoteUser());
        log.info("getRequestURL     : " + httpServletRequest.getRequestURL());
        log.info("getUserPrincipal  : " + httpServletRequest.getUserPrincipal());
        log.info("getAuthType       : " + httpServletRequest.getAuthType());
        log.info("getCookies        : " + Arrays.toString(httpServletRequest.getCookies()));

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
