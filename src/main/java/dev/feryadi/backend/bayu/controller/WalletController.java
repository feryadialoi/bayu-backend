package dev.feryadi.backend.bayu.controller;

import dev.feryadi.backend.bayu.model.request.ListWalletRequest;
import dev.feryadi.backend.bayu.model.response.ApiResponse;
import dev.feryadi.backend.bayu.model.response.WalletBalanceResponse;
import dev.feryadi.backend.bayu.service.WalletService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class WalletController extends BaseController {

    private final WalletService walletService;


    @GetMapping(value = "/api/v1/wallets")
    public ResponseEntity<ApiResponse<List<WalletBalanceResponse>>> getWallets(
            Pageable pageable
    ) {
        ListWalletRequest listWalletRequest = ListWalletRequest.builder()
                .pageable(pageable)
                .build();

        return createResponse(
                HttpStatus.OK,
                "Get wallets successfully",
                walletService.getWallets(listWalletRequest)
        );
    }

    @GetMapping(value = {"/api/v1/wallets/{walletId}"})
    public ResponseEntity<ApiResponse<WalletBalanceResponse>> getWallet(
            @PathVariable(value = "walletId") Long walletId
    ) {
        return createResponse(
                HttpStatus.NO_CONTENT,
                "Get wallet successfully",
                walletService.getWallet(walletId)
        );
    }
}
