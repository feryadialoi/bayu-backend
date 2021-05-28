package dev.feryadi.backend.bayu.serviceimpl;

import dev.feryadi.backend.bayu.command.user.*;
import dev.feryadi.backend.bayu.model.request.CreateUserRequest;
import dev.feryadi.backend.bayu.model.request.ListUserRequest;
import dev.feryadi.backend.bayu.model.request.UpdateUserRequest;
import dev.feryadi.backend.bayu.model.request.command.*;
import dev.feryadi.backend.bayu.model.response.UserResponse;
import dev.feryadi.backend.bayu.service.ServiceExecutor;
import dev.feryadi.backend.bayu.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final ServiceExecutor serviceExecutor;

    @Override
    public UserResponse createUser(CreateUserRequest createUserRequest) {
        return serviceExecutor.execute(CreateUserCommand.class, new CreateUserCommandRequest(createUserRequest));
    }

    @Override
    public UserResponse updateUser(Long userId, UpdateUserRequest updateUserRequest) {
        return serviceExecutor.execute(UpdateUserCommand.class, new UpdateUserCommandRequest(userId, updateUserRequest));
    }

    @Override
    public UserResponse getUser(Long userId) {
        return serviceExecutor.execute(GetUserCommand.class, new GetUserCommandRequest(userId));
    }

    @Override
    public List<UserResponse> getUsers(ListUserRequest listUserRequest) {
        return serviceExecutor.execute(GetUsersCommand.class, new GetUsersCommandRequest(listUserRequest));
    }

    @Override
    public UserResponse getUserByEmail(String email) {
        return serviceExecutor.execute(GetUserByEmailCommand.class, new GetUserByEmailCommandRequest(email));
    }

}
