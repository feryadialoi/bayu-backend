package dev.feryadi.backend.bayu.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.feryadi.backend.bayu.model.request.LoginRequest;
import dev.feryadi.backend.bayu.model.response.ApiResponse;
import dev.feryadi.backend.bayu.model.response.LoginResponse;
import dev.feryadi.backend.bayu.utils.ValidationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


@Slf4j
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final HandlerExceptionResolver handlerExceptionResolver;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final ValidationUtil validationUtil;

    public JwtAuthenticationFilter(
            AuthenticationManager authenticationManager,
            HandlerExceptionResolver handlerExceptionResolver,
            JwtUtil jwtUtil,
            ValidationUtil validationUtil
    ) {
        this.handlerExceptionResolver = handlerExceptionResolver;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.validationUtil = validationUtil;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            LoginRequest loginRequest = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);

            validationUtil.validate(loginRequest);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    loginRequest.getUsername(),
                    loginRequest.getPassword(),
                    new ArrayList<>()
            );

            return authenticationManager.authenticate(authenticationToken);
        } catch (Exception e) {
            log.error("attemptAuthentication.error, " + e);
            handlerExceptionResolver.resolveException(request, response, null, e);
            return null;
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String subject              = authResult.getName();
        Long id                     = ((ApplicationUserDetails) authResult.getPrincipal()).getId();
        String token                = jwtUtil.generateToken(id, subject);

        Object principal = authResult.getPrincipal();
        log.info(principal.toString());

        response.setStatus(HttpStatus.OK.value());

        LoginResponse loginResponse = new LoginResponse(token);

        ApiResponse<LoginResponse> apiResponse = new ApiResponse<>(
                HttpStatus.OK.value(),
                HttpStatus.OK.getReasonPhrase(),
                loginResponse
        );

        ObjectMapper objectMapper = new ObjectMapper();

        Cookie tokenCookie = new Cookie("x-api-token", token);
        tokenCookie.setHttpOnly(true);
        response.addCookie(tokenCookie);
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON.toString());
        response.getWriter().write(objectMapper.writeValueAsString(apiResponse));
        response.getWriter().flush();
    }
}
