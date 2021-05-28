package dev.feryadi.backend.bayu.commandimpl.userrole;

import dev.feryadi.backend.bayu.command.userrole.GetUserRoleCommand;
import dev.feryadi.backend.bayu.entity.User;
import dev.feryadi.backend.bayu.model.request.command.GetUserRoleCommandRequest;
import dev.feryadi.backend.bayu.model.response.UserRoleResponse;
import dev.feryadi.backend.bayu.modelmapper.UserRoleMapper;
import dev.feryadi.backend.bayu.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class GetUserRoleCommandImpl implements GetUserRoleCommand {

    private final UserRepository userRepository;
    private final UserRoleMapper userRoleMapper;


    @Override
    public UserRoleResponse execute(GetUserRoleCommandRequest request) {
        return userRepository.findById(request.getUserId())
                .map(userRoleMapper::mapUserToUserRoleResponse)
                .orElse(null);
    }
}
