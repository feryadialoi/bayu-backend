package dev.feryadi.backend.bayu.serviceimpl;

import dev.feryadi.backend.bayu.command.pin.CreateUserPinCommand;
import dev.feryadi.backend.bayu.command.pin.UpdateUserPinCommand;
import dev.feryadi.backend.bayu.model.request.CreateUserPinRequest;
import dev.feryadi.backend.bayu.model.request.UpdateUserPinRequest;
import dev.feryadi.backend.bayu.model.request.command.CreateUserPinCommandRequest;
import dev.feryadi.backend.bayu.model.request.command.UpdatePinCommandRequest;
import dev.feryadi.backend.bayu.model.response.PinResponse;
import dev.feryadi.backend.bayu.service.UserPinService;
import dev.feryadi.backend.bayu.service.ServiceExecutor;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserPinServiceImpl implements UserPinService {

    private final ServiceExecutor serviceExecutor;


    @Override
    public PinResponse createUserPin(Long useId, CreateUserPinRequest createUserPinRequest) {
        return serviceExecutor.execute(
                CreateUserPinCommand.class,
                new CreateUserPinCommandRequest(useId, createUserPinRequest)
        );
    }

    @Override
    public PinResponse updateUserPin(Long userId, Long pinId, UpdateUserPinRequest updateUserPinRequest) {
        return serviceExecutor.execute(
                UpdateUserPinCommand.class,
                new UpdatePinCommandRequest(userId, pinId, updateUserPinRequest)
        );
    }
}
