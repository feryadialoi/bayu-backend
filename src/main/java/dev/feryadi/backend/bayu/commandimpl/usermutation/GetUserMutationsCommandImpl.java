package dev.feryadi.backend.bayu.commandimpl.usermutation;

import dev.feryadi.backend.bayu.command.usermutation.GetUserMutationsCommand;
import dev.feryadi.backend.bayu.entity.Mutation;
import dev.feryadi.backend.bayu.entity.User;
import dev.feryadi.backend.bayu.entity.Wallet;
import dev.feryadi.backend.bayu.exception.UserNotFoundException;
import dev.feryadi.backend.bayu.exception.WalletNotFoundException;
import dev.feryadi.backend.bayu.model.request.command.GetUserMutationsCommandRequest;
import dev.feryadi.backend.bayu.model.response.UserMutationResponse;
import dev.feryadi.backend.bayu.modelmapper.UserMutationMapper;
import dev.feryadi.backend.bayu.repository.MutationRepository;
import dev.feryadi.backend.bayu.repository.UserRepository;
import dev.feryadi.backend.bayu.specificationandcriteria.specification.MutationSpecification;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
@AllArgsConstructor
public class GetUserMutationsCommandImpl implements GetUserMutationsCommand {

    private final UserRepository userRepository;
    private final MutationRepository mutationRepository;
    private final UserMutationMapper userMutationMapper;

    @Override
    public List<UserMutationResponse> execute(GetUserMutationsCommandRequest request) {
        Optional<User> optionalUser = userRepository.findById(request.getUserId());

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (Objects.nonNull(user.getWallet())) {
                Wallet wallet = user.getWallet();


                Page<Mutation> page = mutationRepository.findAll(
                        MutationSpecification.originWalletIs(wallet),
                        request.getListUserMutationRequest().getPageable()
                );

                List<Mutation> mutations = page.get().collect(Collectors.toList());

                return mutations.stream()
                        .map(userMutationMapper::mapMutationToUserMutationResponse)
                        .collect(Collectors.toList());
            }

            throw new WalletNotFoundException("wallet of user with id " + request.getUserId() + " not found");
        }

        throw new UserNotFoundException("user with id " + request.getUserId() + " not found");
    }
}
