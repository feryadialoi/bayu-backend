package dev.feryadi.backend.bayu.service;

import dev.feryadi.backend.bayu.model.request.CreateUserPinRequest;
import dev.feryadi.backend.bayu.model.request.UpdateUserPinRequest;
import dev.feryadi.backend.bayu.model.response.PinResponse;

public interface UserPinService {
    PinResponse createUserPin(Long userId, CreateUserPinRequest createUserPinRequest);
    PinResponse updateUserPin(Long userId, Long pinId, UpdateUserPinRequest updateUserPinRequest);
}
