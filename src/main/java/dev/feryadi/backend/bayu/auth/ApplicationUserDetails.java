package dev.feryadi.backend.bayu.auth;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class ApplicationUserDetails extends User {

    private final Long id;

    public ApplicationUserDetails(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
    }

    public ApplicationUserDetails(Long id, String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ApplicationUserDetails [" +
                "id=" + id + ", "  +
                "username=" + getUsername() + ", " +
                "password=[PROTECTED], " +
                "enable=" + isEnabled() + ", " +
                "accountNonExpired=" + isAccountNonExpired() + ", " +
                "credentialNonExpired=" + isCredentialsNonExpired() + ", " +
                "accountNonLocked=" + isAccountNonLocked() + ", " +
                "grantedAuthorities=" + getAuthorities().toString() +
                "]";
    }
}