package dev.feryadi.backend.bayu.modelmapperimpl;

import dev.feryadi.backend.bayu.entity.Mutation;
import dev.feryadi.backend.bayu.model.response.UserMutationDetailResponse;
import dev.feryadi.backend.bayu.modelmapper.UserMutationDetailMapper;
import dev.feryadi.backend.bayu.modelmapper.UserMutationWalletDetailMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserMutationDetailMapperImpl implements UserMutationDetailMapper {

    private final UserMutationWalletDetailMapper userMutationWalletDetailMapper;

    @Override
    public UserMutationDetailResponse mapMutationToUserMutationDetailResponse(Mutation mutation) {
        return UserMutationDetailResponse.builder()
                .id(mutation.getId())
                .originWallet(userMutationWalletDetailMapper.mapWalletToUserMutationWalletDetailResponse(mutation.getOriginWallet()))
                .destinationWallet(userMutationWalletDetailMapper.mapWalletToUserMutationWalletDetailResponse(mutation.getDestinationWallet()))
                .amount(mutation.getAmount())
                .description(mutation.getDescription())
                .initialBalance(mutation.getInitialBalance())
                .balance(mutation.getBalance())
                .type(mutation.getType().name())
                .createdDate(mutation.getCreatedDate())
                .build();
    }
}
