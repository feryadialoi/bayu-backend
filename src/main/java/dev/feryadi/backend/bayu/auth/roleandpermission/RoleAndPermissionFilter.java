package dev.feryadi.backend.bayu.auth.roleandpermission;


import dev.feryadi.backend.bayu.auth.ApplicationUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;

@Slf4j
//@Component
public class RoleAndPermissionFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain) throws ServletException, IOException {

        String method = httpServletRequest.getMethod();
        String requestURI = httpServletRequest.getRequestURI();
        Principal userPrincipal = httpServletRequest.getUserPrincipal();
        log.info(method + " : " + requestURI + " by " + userPrincipal);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Object principal = authentication.getPrincipal();

        log.info(principal.toString());

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
