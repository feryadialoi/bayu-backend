package dev.feryadi.backend.bayu.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private final HandlerExceptionResolver handlerExceptionResolver;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().configurationSource(httpServletRequest -> new CorsConfiguration().applyPermitDefaultValues());

        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login").permitAll()
                .antMatchers(HttpMethod.POST, "/register").permitAll()
                .antMatchers(HttpMethod.POST, "/refresh-token").permitAll();

        // allow route for create user/register
        http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/v1/users/**").permitAll();

        // configuration for swagger
        http.authorizeRequests()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/configuration/ui").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/configuration/security").permitAll()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/swagger-ui/*").permitAll()
                .antMatchers("/webjars/**").permitAll();

        // allow security for upload route
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/upload").permitAll()
                .antMatchers(HttpMethod.GET, "/files").permitAll()
                .antMatchers(HttpMethod.GET, "/files/**").permitAll();

        // allow security for static folder file assets
//        http.authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/user-photos/**").permitAll()
//                .antMatchers(HttpMethod.GET, "/uploads/**").permitAll();


        // rest of request must be authenticated
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .addFilter(getJwtAuthorizationFilter())
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private JwtAuthorizationFilter getJwtAuthorizationFilter() throws Exception {
        return new JwtAuthorizationFilter(authenticationManager(), handlerExceptionResolver, jwtUtil, userDetailsService);
    }
}
