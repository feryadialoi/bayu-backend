package dev.feryadi.backend.bayu.modelmapperimpl;

import dev.feryadi.backend.bayu.entity.Mutation;
import dev.feryadi.backend.bayu.model.response.MutationResponse;
import dev.feryadi.backend.bayu.model.response.MutationWalletResponse;
import dev.feryadi.backend.bayu.modelmapper.MutationMapper;
import dev.feryadi.backend.bayu.modelmapper.MutationWalletMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MutationMapperImpl implements MutationMapper {

    private final MutationWalletMapper mutationWalletMapper;


    @Override
    public MutationResponse mapMutationToMutationResponse(Mutation mutation) {
        MutationWalletResponse originWallet = mutationWalletMapper.mapWalletToMutationWallet(mutation.getOriginWallet());
        MutationWalletResponse destinationWallet = mutationWalletMapper.mapWalletToMutationWallet(mutation.getDestinationWallet());

        MutationResponse mutationResponse = new MutationResponse();
        mutationResponse.setOriginWallet(originWallet);
        mutationResponse.setDestinationWallet(destinationWallet);
        mutationResponse.setAmount(mutation.getAmount());
        mutationResponse.setDescription(mutation.getDescription());
        mutationResponse.setCreatedDate(mutation.getCreatedDate());

        return mutationResponse;
    }
}
