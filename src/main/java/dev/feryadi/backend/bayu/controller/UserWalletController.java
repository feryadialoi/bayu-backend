package dev.feryadi.backend.bayu.controller;

import dev.feryadi.backend.bayu.model.request.CreateWalletRequest;
import dev.feryadi.backend.bayu.model.response.ApiResponse;
import dev.feryadi.backend.bayu.model.response.WalletBalanceResponse;
import dev.feryadi.backend.bayu.service.UserWalletService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class UserWalletController extends BaseController {

    private final UserWalletService userWalletService;


    @PreAuthorize("hasRole('ADMIN') or @userSecurity.hasUserId(authentication, #userId)")
    @GetMapping(value = {"/api/v1/users/{userId}/wallets"})
    public ResponseEntity<ApiResponse<WalletBalanceResponse>> getUserWallet(
            @PathVariable(value = "userId") Long userId
    ) {
        return createResponse(
                HttpStatus.OK,
                "Get user's wallet successfully",
                userWalletService.getUserWallet(userId)
        );
    }

    @PreAuthorize("hasRole('ADMIN') or @userSecurity.hasUserId(authentication, #userId)")
    @PostMapping(value = {"/api/v1/users/{userId}/wallets"})
    public ResponseEntity<ApiResponse<WalletBalanceResponse>> createUserWallet(
            @PathVariable(value = "userId") Long userId,
            @Valid @RequestBody CreateWalletRequest createWalletRequest
    ) {
        return createResponse(
                HttpStatus.OK,
                "Create wallet for user with id " + userId + " successfully",
                userWalletService.createUserWallet(userId, createWalletRequest)
        );
    }
}
