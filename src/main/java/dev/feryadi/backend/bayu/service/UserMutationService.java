package dev.feryadi.backend.bayu.service;

import dev.feryadi.backend.bayu.model.request.ListUserMutationRequest;
import dev.feryadi.backend.bayu.model.response.UserMutationResponse;

import java.util.List;

public interface UserMutationService {
    List<UserMutationResponse> getUserMutations(ListUserMutationRequest listUserMutationRequest) throws Exception;
}
