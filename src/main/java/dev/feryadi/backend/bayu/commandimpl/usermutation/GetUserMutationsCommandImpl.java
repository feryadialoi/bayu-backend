package dev.feryadi.backend.bayu.commandimpl.usermutation;

import dev.feryadi.backend.bayu.command.usermutation.GetUserMutationsCommand;
import dev.feryadi.backend.bayu.criteria.SearchCriteria;
import dev.feryadi.backend.bayu.criteria.SearchOperation;
import dev.feryadi.backend.bayu.entity.Mutation;
import dev.feryadi.backend.bayu.entity.User;
import dev.feryadi.backend.bayu.entity.Wallet;
import dev.feryadi.backend.bayu.exception.NotFoundException;
import dev.feryadi.backend.bayu.model.request.ListUserMutationRequest;
import dev.feryadi.backend.bayu.model.request.command.GetUserMutationsCommandRequest;
import dev.feryadi.backend.bayu.model.response.UserMutationResponse;
import dev.feryadi.backend.bayu.modelmapper.UserMutationMapper;
import dev.feryadi.backend.bayu.repository.MutationRepository;
import dev.feryadi.backend.bayu.repository.UserRepository;
import dev.feryadi.backend.bayu.specification.GenericSpecification;
import dev.feryadi.backend.bayu.specification.SpecificationType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Arrays;
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
    public List<UserMutationResponse> execute(GetUserMutationsCommandRequest request) throws Exception {
        Optional<User> optionalUser = userRepository.findById(request.getUserId());

        if (optionalUser.isPresent()) {

            if (Objects.nonNull(optionalUser.get().getWallet())) {
                Wallet wallet = optionalUser.get().getWallet();

                GenericSpecification<Mutation> specification = new GenericSpecification<>(
                        Arrays.asList(
                                SearchCriteria.builder()
                                        .key("originWallet")
                                        .operation(SearchOperation.EQUAL)
                                        .value(wallet)
                                        .build(),
                                SearchCriteria.builder()
                                        .key("destinationWallet")
                                        .operation(SearchOperation.EQUAL)
                                        .value(wallet)
                                        .build()
                        ),
                        SpecificationType.OR
                );


                PageRequest pageRequest = PageRequest.of(
                        request.getListUserMutationRequest().getPage(),
                        request.getListUserMutationRequest().getSize(),
                        Sort.by("createdDate").descending()
                );

                Page<Mutation> page = mutationRepository.findAll(specification, pageRequest);

                List<Mutation> mutations = page.get().collect(Collectors.toList());

                return mutations.stream().map(userMutationMapper::mapMutationToUserMutationResponse).collect(Collectors.toList());
            }

            throw new NotFoundException("wallet of user with id " + request.getUserId() + " not found");
        }

        throw new NotFoundException("user with id " + request.getUserId() + " not found");

//

//        ListUserMutationRequest listUserMutationRequest = request.getListUserMutationRequest();
//
//        PageRequest pageRequest = PageRequest.of(listUserMutationRequest.getPage(), listUserMutationRequest.getSize());
//
//        Page<Mutation> page = mutationRepository.findAll(pageRequest);
//
//        List<Mutation> mutations = page.get().collect(Collectors.toList());
//
//        return mutations.stream().map(userMutationMapper::mapMutationToUserMutationResponse).collect(Collectors.toList());
    }
}
