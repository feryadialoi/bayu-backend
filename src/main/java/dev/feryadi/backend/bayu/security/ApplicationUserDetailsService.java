package dev.feryadi.backend.bayu.security;

import dev.feryadi.backend.bayu.entity.User;
import dev.feryadi.backend.bayu.entity.roleandpermission.Permission;
import dev.feryadi.backend.bayu.entity.roleandpermission.Role;
import dev.feryadi.backend.bayu.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@AllArgsConstructor
public class ApplicationUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return ApplicationUserDetails.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .authorities(getUserAuthorities(user))
                    .accountNonExpired(true)
                    .accountNonLocked(true)
                    .credentialsNonExpired(true)
                    .enabled(true)
                    .build();
        }
        return null;
    }

    private Collection<GrantedAuthority> getUserAuthorities(User user) {
        Role role = user.getRole();
        List<Permission> permissions = role.getPermissions();

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        permissions.forEach(permission -> {
            grantedAuthorities.add(new SimpleGrantedAuthority(permission.getName()));
        });

        return grantedAuthorities;
    }
}
