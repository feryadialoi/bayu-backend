package dev.feryadi.backend.bayu.modelmapper;

import dev.feryadi.backend.bayu.entity.Mutation;
import dev.feryadi.backend.bayu.model.response.UserMutationResponse;

public interface UserMutationMapper {
    UserMutationResponse mapMutationToUserMutationResponse(Mutation mutation);
}
