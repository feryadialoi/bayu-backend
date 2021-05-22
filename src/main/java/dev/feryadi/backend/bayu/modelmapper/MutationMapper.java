package dev.feryadi.backend.bayu.modelmapper;

import dev.feryadi.backend.bayu.entity.Mutation;
import dev.feryadi.backend.bayu.model.response.MutationResponse;

public interface MutationMapper {
    MutationResponse mapMutationToMutationResponse(Mutation mutation);
}
