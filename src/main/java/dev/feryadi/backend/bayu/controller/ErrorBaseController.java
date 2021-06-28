package dev.feryadi.backend.bayu.controller;

import dev.feryadi.backend.bayu.model.response.ApiResponseError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Slf4j
public class ErrorBaseController {
    public <T> ResponseEntity<ApiResponseError<T>> createResponse(HttpStatus httpStatus, String message, T data) {
        log.error(data.toString());
        return new ResponseEntity<>(
                new ApiResponseError<>(httpStatus.value(), httpStatus.getReasonPhrase(), data),
                httpStatus
        );
    }

    public <T> ResponseEntity<ApiResponseError<T>> createResponse(HttpStatus httpStatus, T data) {
        log.error(data.toString());
        return new ResponseEntity<>(
                new ApiResponseError<>(httpStatus.value(), httpStatus.getReasonPhrase(), data),
                httpStatus
        );
    }
}
