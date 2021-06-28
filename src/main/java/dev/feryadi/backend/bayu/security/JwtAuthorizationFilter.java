package dev.feryadi.backend.bayu.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import dev.feryadi.backend.bayu.exception.TokenNotContainUsernameException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@Slf4j
public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    private final JwtUtil jwtUtil;
    private final HandlerExceptionResolver handlerExceptionResolver;
    private final UserDetailsService userDetailsService;

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager,
                                  HandlerExceptionResolver handlerExceptionResolver,
                                  JwtUtil jwtUtil,
                                  UserDetailsService userDetailsService) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
        this.handlerExceptionResolver = handlerExceptionResolver;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
            Cookie[] cookies = request.getCookies();

            boolean hasAuthorization = authorizationHeader != null && authorizationHeader.startsWith("Bearer ");
            boolean hasXApiToken = cookies != null && Arrays.stream(cookies).anyMatch(cookie -> cookie.getName().equals("x-api-token"));

            if (hasAuthorization) {
                authenticateByAuthorizationHeader(request, response, chain);
                chain.doFilter(request, response);
                return;
            }
            if (hasXApiToken) {
                authenticateByXApiToken(request, response, chain);
                chain.doFilter(request, response);
                return;
            }

            chain.doFilter(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            handlerExceptionResolver.resolveException(request, response, null, e);
        }
    }

    private void authenticateByXApiToken(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {
        Cookie[] cookies = request.getCookies();
        Cookie xApiToken = Arrays.stream(cookies).filter(cookie -> cookie.getName().equals("x-api-token")).findFirst().get();
        String token = xApiToken.getValue();
        SecurityContextHolder.getContext().setAuthentication(authenticate(token));
    }

    private void authenticateByAuthorizationHeader(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        log.info(HttpHeaders.AUTHORIZATION + "=" + authorizationHeader);
        String token = authorizationHeader.replace("Bearer ", "");
        SecurityContextHolder.getContext().setAuthentication(authenticate(token));
    }

    private UsernamePasswordAuthenticationToken authenticate(String token) {
        DecodedJWT decodedJWT = jwtUtil.verifyToken(token);
        Long userId = jwtUtil.getUserId(decodedJWT);
        String subject = jwtUtil.getSubject(decodedJWT);

        log.info("Token [subject = \"" + subject + "\", id = \"" + userId + "\"]");

        if (subject == null) {
            throw new TokenNotContainUsernameException("token invalid");
        }

        ApplicationUserDetails userDetails = (ApplicationUserDetails) userDetailsService.loadUserByUsername(subject);

        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
