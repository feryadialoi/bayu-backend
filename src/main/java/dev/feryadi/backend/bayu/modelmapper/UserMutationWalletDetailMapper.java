package dev.feryadi.backend.bayu.modelmapper;

import dev.feryadi.backend.bayu.entity.Wallet;
import dev.feryadi.backend.bayu.model.response.UserMutationWalletDetailResponse;

public interface UserMutationWalletDetailMapper {
    UserMutationWalletDetailResponse mapWalletToUserMutationWalletDetailResponse(Wallet wallet);
}
