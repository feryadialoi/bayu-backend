package dev.feryadi.backend.bayu.modelmapperimpl;

import dev.feryadi.backend.bayu.entity.User;
import dev.feryadi.backend.bayu.model.response.UserRoleResponse;
import dev.feryadi.backend.bayu.modelmapper.RoleMapper;
import dev.feryadi.backend.bayu.modelmapper.UserRoleMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserRoleMapperImpl implements UserRoleMapper {

    private final RoleMapper roleMapper;

    @Override
    public UserRoleResponse mapUserToUserRoleResponse(User user) {
        UserRoleResponse userRoleResponse = new UserRoleResponse();
        userRoleResponse.setName(user.getName());
        userRoleResponse.setUsername(user.getUsername());
        userRoleResponse.setEmail(user.getEmail());
        userRoleResponse.setPhone(user.getPhone());
        userRoleResponse.setRole(roleMapper.mapRoleToRoleResponse(user.getRole()));

        return userRoleResponse;
    }
}
