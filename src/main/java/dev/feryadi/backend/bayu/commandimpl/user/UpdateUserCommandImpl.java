package dev.feryadi.backend.bayu.commandimpl.user;

import dev.feryadi.backend.bayu.command.user.UpdateUserCommand;
import dev.feryadi.backend.bayu.entity.User;
import dev.feryadi.backend.bayu.exception.NotFoundException;
import dev.feryadi.backend.bayu.exception.UserNotFoundException;
import dev.feryadi.backend.bayu.model.request.UpdateUserRequest;
import dev.feryadi.backend.bayu.model.request.command.UpdateUserCommandRequest;
import dev.feryadi.backend.bayu.model.response.UserResponse;
import dev.feryadi.backend.bayu.modelmapper.UserMapper;
import dev.feryadi.backend.bayu.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class UpdateUserCommandImpl implements UpdateUserCommand {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponse execute(UpdateUserCommandRequest updateUserCommandRequest) {

        return userRepository.findById(updateUserCommandRequest.getUserId())
                .map(user -> {
                    UpdateUserRequest updateUserRequest = updateUserCommandRequest.getUpdateUserRequest();

                    if (updateUserRequest.getName() != null) {
                        user.setName(updateUserRequest.getName());
                    }
                    if (updateUserRequest.getUsername() != null) {
                        user.setUsername(updateUserRequest.getUsername());
                    }
                    if (updateUserRequest.getEmail() != null) {
                        user.setEmail(updateUserRequest.getEmail());
                    }
                    if (updateUserRequest.getPhone() != null) {
                        user.setPhone(updateUserRequest.getPhone());
                    }

                    user = userRepository.save(user);

                    return userMapper.mapUserToUserResponse(user);
                })
                .orElseThrow(() -> new UserNotFoundException("User with id " + updateUserCommandRequest.getUserId() + " not found"));


//        Long userId = updateUserCommandRequest.getUserId();
//        UpdateUserRequest updateUserRequest = updateUserCommandRequest.getUpdateUserRequest();
//
//
//        Optional<User> optionalUser = userRepository.findById(userId);
//
//
//        if (optionalUser.isPresent()) {
//            User user = optionalUser.get();
//            if (updateUserRequest.getName() != null) {
//                user.setName(updateUserRequest.getName());
//            }
//            if (updateUserRequest.getUsername() != null) {
//                user.setUsername(updateUserRequest.getUsername());
//            }
//            if (updateUserRequest.getEmail() != null) {
//                user.setEmail(updateUserRequest.getEmail());
//            }
//            if (updateUserRequest.getPhone() != null) {
//                user.setPhone(updateUserRequest.getPhone());
//            }
//
//            user = userRepository.save(user);
//
//            return userMapper.mapUserToUserResponse(user);
//        }
//
//        throw new NotFoundException("User with id " + userId + " not found");

    }
}
