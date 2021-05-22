package dev.feryadi.backend.bayu.auth;

import dev.feryadi.backend.bayu.entity.User;
import dev.feryadi.backend.bayu.entity.roleandpermission.Permission;
import dev.feryadi.backend.bayu.entity.roleandpermission.Role;
import dev.feryadi.backend.bayu.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class ApplicationUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {

        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Collection<GrantedAuthority> userAuthorities = getUserAuthorities(user);
            log.info("userAuthorities: " + userAuthorities);
            return new ApplicationUserDetails(user.getId(), user.getUsername(), user.getPassword(), userAuthorities);
        }

        throw new IllegalArgumentException("username of " + username + " is not found");

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
