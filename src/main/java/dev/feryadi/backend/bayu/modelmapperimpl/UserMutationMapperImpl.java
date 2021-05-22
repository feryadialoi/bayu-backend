package dev.feryadi.backend.bayu.modelmapperimpl;

import dev.feryadi.backend.bayu.entity.Mutation;
import dev.feryadi.backend.bayu.model.response.MutationResponse;
import dev.feryadi.backend.bayu.model.response.UserMutationResponse;
import dev.feryadi.backend.bayu.modelmapper.MutationMapper;
import dev.feryadi.backend.bayu.modelmapper.UserMutationMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserMutationMapperImpl implements UserMutationMapper {
    private final MutationMapper mutationMapper;

    @Override
    public UserMutationResponse mapMutationToUserMutationResponse(Mutation mutation) {
        MutationResponse mutationResponse = mutationMapper.mapMutationToMutationResponse(mutation);

        UserMutationResponse userMutationResponse = new UserMutationResponse();
        userMutationResponse.setMutation(mutationResponse);

        return userMutationResponse;
    }
}
