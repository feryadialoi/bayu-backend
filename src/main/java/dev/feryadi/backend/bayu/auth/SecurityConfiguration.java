package dev.feryadi.backend.bayu.auth;

import dev.feryadi.backend.bayu.utils.ValidationUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true
)
@AllArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final HandlerExceptionResolver handlerExceptionResolver;
    private final ValidationUtil validationUtil;


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors()
                .configurationSource(httpServletRequest -> new CorsConfiguration().applyPermitDefaultValues())
                .and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/testing").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                // configuration for swagger ui
                .antMatchers("/configuration/ui").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/configuration/security").permitAll()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/swagger-ui/*").permitAll()
                .antMatchers("/webjars/**").permitAll();

        List<String> allowedRoutes = new ArrayList<>();
        allowedRoutes.add("/home");
        allowedRoutes.add("/products");
        allowedRoutes.add("/carts");
        allowedRoutes.add("/sales");
        allowedRoutes.add("/categories");
        allowedRoutes.forEach(allowedRoute -> {
            try {
                http.authorizeRequests().antMatchers(allowedRoute).permitAll();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        // allow route for create user/register
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/v1/users/**").permitAll();

        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .addFilter(getJwtAuthenticationFilter())
                .addFilter(getJwtAuthorizationFilter())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    private JwtAuthenticationFilter getJwtAuthenticationFilter() throws Exception {
        return new JwtAuthenticationFilter(authenticationManager(), handlerExceptionResolver, jwtUtil, validationUtil);
    }

    private JwtAuthorizationFilter getJwtAuthorizationFilter() throws Exception {
        return new JwtAuthorizationFilter(authenticationManager(), handlerExceptionResolver, jwtUtil, userDetailsService);
    }
}
