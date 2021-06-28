package dev.feryadi.backend.bayu.controller;

import dev.feryadi.backend.bayu.model.response.ApiResponse;
import dev.feryadi.backend.bayu.model.response.BalanceResponse;
import dev.feryadi.backend.bayu.service.UserBalanceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserBalanceController extends BaseController {

    private final UserBalanceService userBalanceService;

    @PreAuthorize("hasRole('ADMIN') or @userSecurity.hasUserId(authentication, #userId)")
    @GetMapping(value = "/api/v1/users/{userId}/balances")
    public ResponseEntity<ApiResponse<BalanceResponse>> getUserBalance(
            @PathVariable(value = "userId") Long userId
    ) {
        return createResponse(
                "Get user balance successfully",
                userBalanceService.getUserBalance(userId)
        );
    }
}
