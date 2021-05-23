package dev.feryadi.backend.bayu.modelmapper;

import dev.feryadi.backend.bayu.entity.Mutation;
import dev.feryadi.backend.bayu.model.response.UserMutationDetailResponse;

public interface UserMutationDetailMapper {
    UserMutationDetailResponse mapMutationToUserMutationDetailResponse(Mutation mutation);
}
