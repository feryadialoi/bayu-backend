package dev.feryadi.backend.bayu.controller;

import dev.feryadi.backend.bayu.exception.NotFoundException;
import dev.feryadi.backend.bayu.model.ApiResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.converter.HttpMessageNotReadableException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class ErrorController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseError<Map<String, String>>> methodArgumentNotValid(MethodArgumentNotValidException methodArgumentNotValidException) {

        Map<String, String> error = new HashMap<>();
        methodArgumentNotValidException.getBindingResult().getAllErrors().forEach((e) -> {
            String fieldName = ((FieldError) e).getField();
            String errorMessage = e.getDefaultMessage();
            error.put(fieldName, errorMessage);
        });

        ApiResponseError<Map<String, String>> apiResponseError = new ApiResponseError<>(422, "UNPROCESSABLE ENTITY", error);

        return new ResponseEntity<>(apiResponseError, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ApiResponseError<String>> sqlIntegrityConstraintViolation(SQLIntegrityConstraintViolationException sqlIntegrityConstraintViolationException) {
        ApiResponseError<String> apiResponseError = new ApiResponseError<>(409, "CONFLICT", sqlIntegrityConstraintViolationException.getMessage());

        return new ResponseEntity<>(apiResponseError, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponseError<String>> httpMessageNotReadable(HttpMessageNotReadableException httpMessageNotReadableException) {
        ApiResponseError<String> apiResponseError = new ApiResponseError<>(400, "BAD REQUEST", httpMessageNotReadableException.getMessage());

        return new ResponseEntity<>(apiResponseError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponseError<String>> notFound(NotFoundException notFoundException) {
        ApiResponseError<String> apiResponseError = new ApiResponseError<>(404, "NOT FOUNT", notFoundException.getMessage());

        return new ResponseEntity<>(apiResponseError, HttpStatus.NOT_FOUND);
    }
}
