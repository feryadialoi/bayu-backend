package dev.feryadi.backend.bayu.commandimpl.user;

import dev.feryadi.backend.bayu.command.user.GetUserCommand;
import dev.feryadi.backend.bayu.entity.User;
import dev.feryadi.backend.bayu.model.request.command.GetUserCommandRequest;
import dev.feryadi.backend.bayu.model.response.UserResponse;
import dev.feryadi.backend.bayu.modelmapper.UserMapper;
import dev.feryadi.backend.bayu.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class GetUserCommandImpl implements GetUserCommand {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public UserResponse execute(GetUserCommandRequest getUserCommandRequest) throws Exception {
        return userRepository.findById(getUserCommandRequest.getUserId())
                .map(userMapper::mapUserToUserResponse)
                .orElse(null);

//        Long userId = getUserCommandRequest.getUserId();
//        Optional<User> user = userRepository.findById(userId);
//        return user.map(userMapper::mapUserToUserResponse).orElse(null);
    }
}
