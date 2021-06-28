package dev.feryadi.backend.bayu.modelmapperimpl;

import dev.feryadi.backend.bayu.entity.Mutation;
import dev.feryadi.backend.bayu.model.response.UserMutationResponse;
import dev.feryadi.backend.bayu.modelmapper.MutationWalletMapper;
import dev.feryadi.backend.bayu.modelmapper.UserMutationMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserMutationMapperImpl implements UserMutationMapper {

    private final MutationWalletMapper mutationWalletMapper;


    @Override
    public UserMutationResponse mapMutationToUserMutationResponse(Mutation mutation) {
        return UserMutationResponse.builder()
                .id(mutation.getId())
                .originWallet(mutationWalletMapper.mapWalletToMutationWallet(mutation.getSenderWallet()))
                .destinationWallet(mutationWalletMapper.mapWalletToMutationWallet(mutation.getReceiverWallet()))
                .amount(mutation.getAmount())
                .description(mutation.getDescription())
                .initialBalance(mutation.getInitialBalance())
                .balance(mutation.getBalance())
                .type(mutation.getType().name())
                .createdDate(mutation.getCreatedDate())
                .build();
    }
}
