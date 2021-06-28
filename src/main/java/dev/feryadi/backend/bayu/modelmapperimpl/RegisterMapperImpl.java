package dev.feryadi.backend.bayu.modelmapperimpl;

import dev.feryadi.backend.bayu.entity.User;
import dev.feryadi.backend.bayu.model.response.RegisterResponse;
import dev.feryadi.backend.bayu.modelmapper.RegisterMapper;
import org.springframework.stereotype.Component;

@Component
public class RegisterMapperImpl implements RegisterMapper {

    @Override
    public RegisterResponse mapUserToRegisterResponse(User user) {
        return RegisterResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .username(user.getUsername())
                .email(user.getEmail())
                .build();
    }
}
