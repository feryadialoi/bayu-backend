package dev.feryadi.backend.bayu.serviceimpl;

import dev.feryadi.backend.bayu.command.userrole.GetUserRoleCommand;
import dev.feryadi.backend.bayu.entity.User;
import dev.feryadi.backend.bayu.model.request.command.GetUserRoleCommandRequest;
import dev.feryadi.backend.bayu.model.response.UserRoleResponse;
import dev.feryadi.backend.bayu.modelmapper.UserRoleMapper;
import dev.feryadi.backend.bayu.repository.UserRepository;
import dev.feryadi.backend.bayu.service.ServiceExecutor;
import dev.feryadi.backend.bayu.service.UserRoleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserRoleServiceImpl implements UserRoleService {

    private final ServiceExecutor serviceExecutor;

    @Override
    public UserRoleResponse getUserRole(Long userId) {
        return serviceExecutor.execute(GetUserRoleCommand.class, new GetUserRoleCommandRequest(userId));
    }
}
