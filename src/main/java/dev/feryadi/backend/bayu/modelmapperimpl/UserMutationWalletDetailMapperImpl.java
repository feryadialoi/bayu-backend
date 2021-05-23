package dev.feryadi.backend.bayu.modelmapperimpl;

import dev.feryadi.backend.bayu.entity.Wallet;
import dev.feryadi.backend.bayu.model.response.UserMutationWalletDetailResponse;
import dev.feryadi.backend.bayu.modelmapper.UserMutationWalletDetailMapper;
import dev.feryadi.backend.bayu.modelmapper.UserMutationWalletUserDetailMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserMutationWalletDetailMapperImpl implements UserMutationWalletDetailMapper {

    private final UserMutationWalletUserDetailMapper userMutationWalletUserDetailMapper;

    @Override
    public UserMutationWalletDetailResponse mapWalletToUserMutationWalletDetailResponse(Wallet wallet) {
        return UserMutationWalletDetailResponse.builder()
                .id(wallet.getId())
                .address(wallet.getAddress())
                .user(userMutationWalletUserDetailMapper.mapUserToUserMutationWalletUserDetailResponse(wallet.getUser()))
                .build();
    }
}
