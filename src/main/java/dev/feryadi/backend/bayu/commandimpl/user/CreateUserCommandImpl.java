package dev.feryadi.backend.bayu.commandimpl.user;

import dev.feryadi.backend.bayu.command.user.CreateUserCommand;
import dev.feryadi.backend.bayu.entity.User;
import dev.feryadi.backend.bayu.exception.RoleNotFoundException;
import dev.feryadi.backend.bayu.model.request.CreateUserRequest;
import dev.feryadi.backend.bayu.model.request.command.CreateUserCommandRequest;
import dev.feryadi.backend.bayu.model.response.UserResponse;
import dev.feryadi.backend.bayu.modelmapper.UserMapper;
import dev.feryadi.backend.bayu.repository.RoleRepository;
import dev.feryadi.backend.bayu.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CreateUserCommandImpl implements CreateUserCommand {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse execute(CreateUserCommandRequest createUserCommandRequest) {
        return roleRepository.findRoleByName("USER")
                .map(role -> {
                    CreateUserRequest createUserRequest = createUserCommandRequest.getCreateUserRequest();

                    User user = new User();
                    user.setName(createUserRequest.getName());
                    user.setUsername(createUserRequest.getUsername());
                    user.setEmail(createUserRequest.getEmail());
                    user.setPhone(createUserRequest.getPhone());
                    user.setRole(role);
                    user.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));

                    user = userRepository.save(user);

                    return userMapper.mapUserToUserResponse(user);

                })
                .orElseThrow(() -> new RoleNotFoundException(""));

//        CreateUserRequest createUserRequest = createUserCommandRequest.getCreateUserRequest();
//
//        Optional<Role> role = roleRepository.findRoleByName("USER");
//
//        User user = new User();
//        user.setName(createUserRequest.getName());
//        user.setUsername(createUserRequest.getUsername());
//        user.setEmail(createUserRequest.getEmail());
//        user.setPhone(createUserRequest.getPhone());
//        role.ifPresent(user::setRole);
//        user.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
//
//        userRepository.save(user);
//
//        return userMapper.mapUserToUserResponse(user);
    }
}
