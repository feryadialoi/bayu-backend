package dev.feryadi.backend.bayu.commandimpl.pin;

import dev.feryadi.backend.bayu.command.pin.CreateUserPinCommand;
import dev.feryadi.backend.bayu.entity.Pin;
import dev.feryadi.backend.bayu.entity.User;
import dev.feryadi.backend.bayu.exception.UserNotFoundException;
import dev.feryadi.backend.bayu.model.request.command.CreateUserPinCommandRequest;
import dev.feryadi.backend.bayu.model.response.PinResponse;
import dev.feryadi.backend.bayu.modelmapper.PinMapper;
import dev.feryadi.backend.bayu.repository.PinRepository;
import dev.feryadi.backend.bayu.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class CreateUserPinCommandImpl implements CreateUserPinCommand {

    private final PinRepository pinRepository;
    private final UserRepository userRepository;
    private final PinMapper pinMapper;

    @Override
    public PinResponse execute(CreateUserPinCommandRequest request) {

        Optional<User> optionalUser = userRepository.findById(request.getUserId());
        if (optionalUser.isPresent()) {
            Pin pin = Pin.builder()
                    .pin(request.getCreateUserPinRequest().getPin())
                    .user(optionalUser.get())
                    .build();

            pin = pinRepository.save(pin);

            return pinMapper.mapPinToPinResponse(pin);
        }

        throw new UserNotFoundException("user with id " + request.getUserId() + " not found");

    }
}
