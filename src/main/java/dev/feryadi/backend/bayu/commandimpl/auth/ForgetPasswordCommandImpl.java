package dev.feryadi.backend.bayu.commandimpl.auth;

import dev.feryadi.backend.bayu.command.auth.ForgetPasswordCommand;
import dev.feryadi.backend.bayu.entity.User;
import dev.feryadi.backend.bayu.exception.EmailNotFoundException;
import dev.feryadi.backend.bayu.model.request.ForgetPasswordRequest;
import dev.feryadi.backend.bayu.model.request.command.ForgetPasswordCommandRequest;
import dev.feryadi.backend.bayu.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class ForgetPasswordCommandImpl implements ForgetPasswordCommand {

    private final UserRepository userRepository;

    @Override
    public Void execute(ForgetPasswordCommandRequest request) {
        ForgetPasswordRequest forgetPasswordRequest = request.getForgetPasswordRequest();

        Optional<User> optionalUser = userRepository.findByEmail(forgetPasswordRequest.getEmail());
        if (!optionalUser.isPresent()) {
            throw new EmailNotFoundException("email not found");
        }

        // send email reset password

        return null;
    }
}
