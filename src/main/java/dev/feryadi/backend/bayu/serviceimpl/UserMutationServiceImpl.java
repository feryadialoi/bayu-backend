package dev.feryadi.backend.bayu.serviceimpl;

import dev.feryadi.backend.bayu.command.usermutation.GetUserMutationDetailCommand;
import dev.feryadi.backend.bayu.command.usermutation.GetUserMutationsCommand;
import dev.feryadi.backend.bayu.entity.Mutation;
import dev.feryadi.backend.bayu.model.request.GetUserMutationDetailCommandRequest;
import dev.feryadi.backend.bayu.model.request.ListUserMutationRequest;
import dev.feryadi.backend.bayu.model.request.command.GetUserMutationsCommandRequest;
import dev.feryadi.backend.bayu.model.response.UserMutationDetailResponse;
import dev.feryadi.backend.bayu.model.response.UserMutationResponse;
import dev.feryadi.backend.bayu.modelmapper.UserMutationMapper;
import dev.feryadi.backend.bayu.repository.MutationRepository;
import dev.feryadi.backend.bayu.service.ServiceExecutor;
import dev.feryadi.backend.bayu.service.UserMutationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserMutationServiceImpl implements UserMutationService {

    private final ServiceExecutor serviceExecutor;

    @Override
    public List<UserMutationResponse> getUserMutations(Long userId,
                                                       ListUserMutationRequest listUserMutationRequest
    ) throws Exception {
        return serviceExecutor.execute(
                GetUserMutationsCommand.class,
                new GetUserMutationsCommandRequest(userId, listUserMutationRequest)
        );
    }

    @Override
    public UserMutationDetailResponse getUserMutation(Long userId, Long mutationId) throws Exception {
        return serviceExecutor.execute(
                GetUserMutationDetailCommand.class,
                new GetUserMutationDetailCommandRequest(userId, mutationId)
        );
    }
}
