package dev.feryadi.backend.bayu.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class InterceptorConfig implements WebMvcConfigurer {

    private final UserRouteInterceptor userRouteInterceptor;

    @Autowired
    public InterceptorConfig(UserRouteInterceptor userRouteInterceptor) {
        this.userRouteInterceptor = userRouteInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);

//        registry.addWebRequestInterceptor(userRouteInterceptor)
//                .addPathPatterns("/api/v1/users/**");
    }
}
