package dev.feryadi.backend.bayu.controller;

import dev.feryadi.backend.bayu.exception.*;
import dev.feryadi.backend.bayu.model.NotValidDetail;
import dev.feryadi.backend.bayu.model.response.ApiResponseError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class ErrorController extends ErrorBaseController {
    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<ApiResponseError<String>> userNotFound(UserNotFoundException userNotFoundException) {
        return createResponse(HttpStatus.NOT_FOUND, "", userNotFoundException.getMessage());
    }

    @ExceptionHandler(value = {WalletNotFoundException.class})
    public ResponseEntity<ApiResponseError<String>> walletNotFound(WalletNotFoundException walletNotFoundException) {
        return createResponse(HttpStatus.NOT_FOUND, "", walletNotFoundException.getMessage());
    }

    @ExceptionHandler(value = {MutationNotFoundException.class})
    public ResponseEntity<ApiResponseError<String>> mutationNotFound(MutationNotFoundException mutationNotFoundException) {
        return createResponse(HttpStatus.NOT_FOUND, "", mutationNotFoundException.getMessage());
    }

    @ExceptionHandler(value = {TransactionNotFoundException.class})
    public ResponseEntity<ApiResponseError<String>> transactionNotFound(TransactionNotFoundException transactionNotFoundException) {
        return createResponse(HttpStatus.NOT_FOUND, "", transactionNotFoundException.getMessage());
    }

    @ExceptionHandler(value = {UserWalletAlreadyExistsException.class})
    public ResponseEntity<ApiResponseError<String>> userWalletAlreadyExists(UserWalletAlreadyExistsException userWalletAlreadyExistsException) {
        return createResponse(HttpStatus.CONFLICT, "", userWalletAlreadyExistsException.getMessage());
    }

    @ExceptionHandler(value = {IllegalStateException.class})
    public ResponseEntity<ApiResponseError<String>> illegalState(
            IllegalStateException illegalStateException
    ) {
        return createResponse(HttpStatus.BAD_REQUEST, "", illegalStateException.getMessage());
    }

    @ExceptionHandler(value = {ForbiddenAccessException.class})
    public ResponseEntity<ApiResponseError<String>> forbiddenAccess(
            ForbiddenAccessException forbiddenAccessException
    ) {
        return createResponse(HttpStatus.FORBIDDEN, "", forbiddenAccessException.getMessage());
    }

    @ExceptionHandler(value = {AlreadyExistException.class})
    public ResponseEntity<ApiResponseError<String>> alreadyExist(
            AlreadyExistException alreadyExistException
    ) {
        return createResponse(HttpStatus.CONFLICT, "", alreadyExistException.getMessage());
    }

    @ExceptionHandler(value = {ValidationNotValidException.class})
    public ResponseEntity<ApiResponseError<List<NotValidDetail>>> validationNotValid(
            ValidationNotValidException validationNotValidException
    ) {
        return createResponse(HttpStatus.UNPROCESSABLE_ENTITY, "", validationNotValidException.getNotValidDetails());
    }

    @ExceptionHandler(value = {ConstraintViolationException.class})
    public ResponseEntity<ApiResponseError<String>> constraintViolation(
            ConstraintViolationException constraintViolationException
    ) {
        return createResponse(HttpStatus.UNPROCESSABLE_ENTITY, "", constraintViolationException.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseError<List<NotValidDetail>>> methodArgumentNotValid(
            MethodArgumentNotValidException methodArgumentNotValidException
    ) {

        Map<String, String> error = new HashMap<>();
        methodArgumentNotValidException
                .getBindingResult()
                .getAllErrors()
                .forEach((e) -> {
                    String fieldName = ((FieldError) e).getField();
                    String errorMessage = e.getDefaultMessage();
                    error.put(fieldName, errorMessage);
                });

        List<NotValidDetail> notValidDetails = methodArgumentNotValidException
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(objectError -> {
                    NotValidDetail notValidDetail = new NotValidDetail();
                    notValidDetail.setProperty(((FieldError) objectError).getField());
                    notValidDetail.setErrorMessage(objectError.getDefaultMessage());
                    return notValidDetail;
                })
                .collect(Collectors.toList());

        return createResponse(HttpStatus.UNPROCESSABLE_ENTITY, "", notValidDetails);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ApiResponseError<String>> sqlIntegrityConstraintViolation(
            SQLIntegrityConstraintViolationException sqlIntegrityConstraintViolationException
    ) {
        return createResponse(HttpStatus.CONFLICT, "", sqlIntegrityConstraintViolationException.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponseError<String>> httpMessageNotReadable(
            HttpMessageNotReadableException httpMessageNotReadableException
    ) {
        return createResponse(HttpStatus.BAD_REQUEST, "", httpMessageNotReadableException.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponseError<String>> notFound(
            NotFoundException notFoundException
    ) {
        return createResponse(HttpStatus.NOT_FOUND, "", notFoundException.getMessage());
    }
}
