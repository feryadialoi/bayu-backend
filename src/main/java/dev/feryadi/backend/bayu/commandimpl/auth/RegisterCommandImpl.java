package dev.feryadi.backend.bayu.commandimpl.auth;

import dev.feryadi.backend.bayu.command.auth.RegisterCommand;
import dev.feryadi.backend.bayu.entity.User;
import dev.feryadi.backend.bayu.entity.roleandpermission.Role;
import dev.feryadi.backend.bayu.exception.RoleNotFoundException;
import dev.feryadi.backend.bayu.model.request.RegisterRequest;
import dev.feryadi.backend.bayu.model.request.command.RegisterCommandRequest;
import dev.feryadi.backend.bayu.model.response.RegisterResponse;
import dev.feryadi.backend.bayu.modelmapper.RegisterMapper;
import dev.feryadi.backend.bayu.repository.RoleRepository;
import dev.feryadi.backend.bayu.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class RegisterCommandImpl implements RegisterCommand {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final RegisterMapper registerMapper;


    @Override
    public RegisterResponse execute(RegisterCommandRequest commandRequest) {

        RegisterRequest registerRequest = commandRequest.getRegisterRequest();

        Optional<Role> optionalRole = roleRepository.findRoleByName("USER");

        if (!optionalRole.isPresent()) {
            throw new RoleNotFoundException("");
        }

        User user = new User();
        user.setName(registerRequest.getName());
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPhone(registerRequest.getPhone());
        user.setRole(optionalRole.get());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        user = userRepository.save(user);

        return registerMapper.mapUserToRegisterResponse(user);
    }
}
