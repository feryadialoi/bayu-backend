package dev.feryadi.backend.bayu.commandimpl.user;

import dev.feryadi.backend.bayu.command.user.GetUserByEmailCommand;
import dev.feryadi.backend.bayu.entity.User;
import dev.feryadi.backend.bayu.model.request.command.GetUserByEmailCommandRequest;
import dev.feryadi.backend.bayu.model.response.UserResponse;
import dev.feryadi.backend.bayu.modelmapper.UserMapper;
import dev.feryadi.backend.bayu.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class GetUserByEmailCommandImpl implements GetUserByEmailCommand {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public UserResponse execute(GetUserByEmailCommandRequest getUserByEmailCommandRequest) {
        return userRepository.findByEmail(getUserByEmailCommandRequest.getEmail())
                .map(userMapper::mapUserToUserResponse)
                .orElse(null);

//        String email = getUserByEmailCommandRequest.getEmail();
//        Optional<User> user = userRepository.findByEmail(email);
//        return user.map(userMapper::mapUserToUserResponse).orElse(null);
    }
}
