package dev.feryadi.backend.bayu.commandimpl.auth;

import dev.feryadi.backend.bayu.command.auth.ChangePasswordCommand;
import dev.feryadi.backend.bayu.entity.User;
import dev.feryadi.backend.bayu.exception.OldPasswordNotMatchException;
import dev.feryadi.backend.bayu.exception.UserNotFoundException;
import dev.feryadi.backend.bayu.model.request.ChangePasswordRequest;
import dev.feryadi.backend.bayu.model.request.command.ChangePasswordCommandRequest;
import dev.feryadi.backend.bayu.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class ChangePasswordCommandImpl implements ChangePasswordCommand {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Void execute(ChangePasswordCommandRequest request) {

        Long userId = request.getUserId();
        ChangePasswordRequest changePasswordRequest = request.getChangePasswordRequest();

        // check if user exists
        Optional<User> optionalUser = userRepository.findById(userId);

        if (!optionalUser.isPresent()) {
            throw new UserNotFoundException("user with id " + userId + " not found");
        }

        User user = optionalUser.get();

        // check old password match
        if (!passwordEncoder.matches(user.getPassword(), changePasswordRequest.getOldPassword())) {
            throw new OldPasswordNotMatchException("old password not match");
        }

        // update user password with new password
        user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));

        userRepository.save(user);

        return null;
    }
}
