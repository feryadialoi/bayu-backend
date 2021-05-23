package dev.feryadi.backend.bayu.modelmapper;

import dev.feryadi.backend.bayu.entity.User;
import dev.feryadi.backend.bayu.model.response.UserMutationWalletUserDetailResponse;

public interface UserMutationWalletUserDetailMapper {
    UserMutationWalletUserDetailResponse mapUserToUserMutationWalletUserDetailResponse(User user);
}
