package dev.feryadi.backend.bayu.config;


import dev.feryadi.backend.bayu.security.ApplicationUserDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditingConfig {
    @Bean
    public AuditorAware<Long> auditorProvider() {
        return new AuditorAware<Long>() {
            @Override
            public Optional<Long> getCurrentAuditor() {
                if (SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null) {
                    if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser") {
                        return Optional.ofNullable(SecurityContextHolder.getContext())
                                .map(SecurityContext::getAuthentication)
                                .filter(Authentication::isAuthenticated)
                                .map(Authentication::getPrincipal)
                                .map(ApplicationUserDetails.class::cast)
                                .map(ApplicationUserDetails::getId);
                    }

                    return Optional.empty();
                }
                return Optional.empty();
            }
        };
    }
}
