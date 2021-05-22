package dev.feryadi.backend.bayu.commandimpl.usermutation;

import dev.feryadi.backend.bayu.command.usermutation.GetUserMutationsCommand;
import dev.feryadi.backend.bayu.entity.Mutation;
import dev.feryadi.backend.bayu.model.request.ListUserMutationRequest;
import dev.feryadi.backend.bayu.model.request.command.GetUserMutationsCommandRequest;
import dev.feryadi.backend.bayu.model.response.UserMutationResponse;
import dev.feryadi.backend.bayu.modelmapper.UserMutationMapper;
import dev.feryadi.backend.bayu.repository.MutationRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class GetUserMutationsCommandImpl implements GetUserMutationsCommand {

    private final UserMutationMapper userMutationMapper;
    private final MutationRepository mutationRepository;


    @Override
    public List<UserMutationResponse> execute(GetUserMutationsCommandRequest request) throws Exception {
        ListUserMutationRequest listUserMutationRequest = request.getListUserMutationRequest();

        PageRequest pageRequest = PageRequest.of(listUserMutationRequest.getPage(), listUserMutationRequest.getSize());

        Page<Mutation> page = mutationRepository.findAll(pageRequest);

        List<Mutation> mutations = page.get().collect(Collectors.toList());

        return mutations.stream().map(userMutationMapper::mapMutationToUserMutationResponse).collect(Collectors.toList());
    }
}
