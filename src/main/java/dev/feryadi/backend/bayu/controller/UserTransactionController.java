package dev.feryadi.backend.bayu.controller;

import dev.feryadi.backend.bayu.exception.NotFoundException;
import dev.feryadi.backend.bayu.model.request.ListUserTransactionRequest;
import dev.feryadi.backend.bayu.model.response.ApiResponse;
import dev.feryadi.backend.bayu.model.response.UserTransactionResponse;
import dev.feryadi.backend.bayu.service.UserTransactionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserTransactionController extends BaseController {

    private final UserTransactionService userTransactionService;


    @PreAuthorize("hasRole('ADMIN') or @userSecurity.hasUserId(authentication, #userId)")
    @GetMapping(value = {"/api/v1/users/{userId}/transactions"})
    public ResponseEntity<ApiResponse<List<UserTransactionResponse>>> getUserTransactions(
            @PathVariable(value = "userId") Long userId,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size
    ) throws Exception {
        ListUserTransactionRequest listUserTransactionRequest = ListUserTransactionRequest.builder()
                .page(page)
                .size(size)
                .build();

        return createResponse(
                HttpStatus.OK,
                "Get user transactions successfully",
                userTransactionService.getUserTransactions(userId, listUserTransactionRequest)
        );
    }

    @PreAuthorize("hasRole('ADMIN') or @userSecurity.hasUserId(authentication, #userId)")
    @GetMapping(value = {"/api/v1/users/{userId}/transactions/{transactionId}"})
    public ResponseEntity<ApiResponse<UserTransactionResponse>> getUserTransaction(
            @PathVariable(value = "userId") Long userId,
            @PathVariable(value = "transactionId") Long transactionId
    ) throws Exception {
        return createResponse(
                HttpStatus.OK,
                "Get user transactions successfully",
                userTransactionService.getUserTransaction(userId, transactionId)
        );
    }
}
