package dev.feryadi.backend.bayu.modelmapperimpl;

import dev.feryadi.backend.bayu.entity.User;
import dev.feryadi.backend.bayu.model.response.UserMutationWalletUserDetailResponse;
import dev.feryadi.backend.bayu.modelmapper.UserMutationWalletUserDetailMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMutationWalletUserDetailMapperImpl implements UserMutationWalletUserDetailMapper {
    @Override
    public UserMutationWalletUserDetailResponse mapUserToUserMutationWalletUserDetailResponse(User user) {
        return UserMutationWalletUserDetailResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }
}
