package dev.feryadi.backend.bayu.modelmapperimpl;

import dev.feryadi.backend.bayu.entity.Mutation;
import dev.feryadi.backend.bayu.model.response.MutationResponse;
import dev.feryadi.backend.bayu.model.response.MutationWalletResponse;
import dev.feryadi.backend.bayu.model.response.UserMutationResponse;
import dev.feryadi.backend.bayu.modelmapper.MutationMapper;
import dev.feryadi.backend.bayu.modelmapper.MutationWalletMapper;
import dev.feryadi.backend.bayu.modelmapper.UserMutationMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserMutationMapperImpl implements UserMutationMapper {

    private final MutationWalletMapper mutationWalletMapper;


    @Override
    public UserMutationResponse mapMutationToUserMutationResponse(Mutation mutation) {
        return UserMutationResponse.builder()
                .id(mutation.getId())
                .originWallet(mutationWalletMapper.mapWalletToMutationWallet(mutation.getOriginWallet()))
                .destinationWallet(mutationWalletMapper.mapWalletToMutationWallet(mutation.getDestinationWallet()))
                .amount(mutation.getAmount())
                .description(mutation.getDescription())
                .createdDate(mutation.getCreatedDate())
                .build();
    }
}
