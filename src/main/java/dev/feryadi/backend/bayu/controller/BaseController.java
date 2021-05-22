package dev.feryadi.backend.bayu.controller;

import dev.feryadi.backend.bayu.model.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseController {
    public <T> ResponseEntity<ApiResponse<T>> createResponse(HttpStatus httpStatus, String message, T data) {
        return new ResponseEntity<>(
                new ApiResponse<>(httpStatus.value(), message, data),
                httpStatus
        );
    }
}
