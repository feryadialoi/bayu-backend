package dev.feryadi.backend.bayu.security;


import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Slf4j
@Component("userSecurity")
public class UserSecurity {
    public boolean hasUserId(Authentication authentication, Long userId) {
        ApplicationUserDetails userDetails = (ApplicationUserDetails) authentication.getPrincipal();

        log.info(userDetails.toString());
        log.info("userId=" + userId);

        return userDetails.getId().equals(userId);
    }
}
