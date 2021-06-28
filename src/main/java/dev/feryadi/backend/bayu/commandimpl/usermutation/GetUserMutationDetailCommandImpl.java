package dev.feryadi.backend.bayu.commandimpl.usermutation;

import dev.feryadi.backend.bayu.command.usermutation.GetUserMutationDetailCommand;
import dev.feryadi.backend.bayu.entity.Mutation;
import dev.feryadi.backend.bayu.entity.User;
import dev.feryadi.backend.bayu.entity.Wallet;
import dev.feryadi.backend.bayu.exception.MutationNotFoundException;
import dev.feryadi.backend.bayu.exception.UserNotFoundException;
import dev.feryadi.backend.bayu.model.request.GetUserMutationDetailCommandRequest;
import dev.feryadi.backend.bayu.model.response.UserMutationDetailResponse;
import dev.feryadi.backend.bayu.modelmapper.UserMutationDetailMapper;
import dev.feryadi.backend.bayu.repository.MutationRepository;
import dev.feryadi.backend.bayu.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@AllArgsConstructor
public class GetUserMutationDetailCommandImpl implements GetUserMutationDetailCommand {

    private final UserRepository userRepository;
    private final MutationRepository mutationRepository;
    private final UserMutationDetailMapper userMutationDetailMapper;

    @Override
    public UserMutationDetailResponse execute(GetUserMutationDetailCommandRequest request) {

        Optional<User> optionalUser = userRepository.findById(request.getUserId());
        Optional<Mutation> optionalMutation = mutationRepository.findById(request.getMutationId());

        if (optionalUser.isPresent()) {
            if (optionalMutation.isPresent()) {
                User user = optionalUser.get();
                Wallet wallet = user.getWallet();
                Mutation mutation = optionalMutation.get();

                if (mutation.getSenderWallet().equals(wallet) || mutation.getReceiverWallet().equals(wallet)) {
                    return userMutationDetailMapper.mapMutationToUserMutationDetailResponse(mutation);
                }

                throw new MutationNotFoundException("user mutation id " + request.getUserId() + " not found");

            }

            throw new MutationNotFoundException("user mutation id " + request.getUserId() + " not found");

        }

        throw new UserNotFoundException("user with id " + request.getUserId() + " not found");

    }
}
