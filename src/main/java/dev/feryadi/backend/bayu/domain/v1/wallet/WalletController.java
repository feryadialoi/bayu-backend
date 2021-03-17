/*
  Author - Feryadi Aloi
  Github - https://github.com/feryadialoi
 */

package dev.feryadi.backend.bayu.domain.v1.wallet;

import dev.feryadi.backend.bayu.model.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Null;
import java.util.List;

@RestController
@RequestMapping("/api/v1/wallets")
public class WalletController {
    private final WalletService walletService;

    @Autowired
    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }


    @GetMapping
    public ResponseEntity<ApiResponse<List<WalletResponse>>> getListOfWallet() {
        List<WalletResponse> listOfWalletResponse = walletService.getListOfWallet();

        ApiResponse<List<WalletResponse>> apiResponse = new ApiResponse<>(200, "OK", listOfWalletResponse);

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{walletId}")
    public ResponseEntity<ApiResponse<WalletResponse>> getWallet(@PathVariable("walletId") Long walletId) throws Exception {
        WalletResponse walletResponse = walletService.getWallet(walletId);

        ApiResponse<WalletResponse> apiResponse = new ApiResponse<>(200, "OK", walletResponse);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ApiResponse<WalletResponse>> storeWallet(
            @Valid
            @RequestBody CreateWalletRequest createWalletRequest
    ) {
        WalletResponse walletResponse = walletService.storeWallet(createWalletRequest);

        ApiResponse<WalletResponse> apiResponse = new ApiResponse<>(201, "CREATED", walletResponse);

        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{walletId}")
    public ResponseEntity<ApiResponse<WalletResponse>> updateWallet(
            @RequestBody UpdateWalletRequest updateWalletRequest,
            @PathVariable("walletId") Long walletId
    ) throws Exception {
        WalletResponse walletResponse = walletService.updateWallet(updateWalletRequest, walletId);

        ApiResponse<WalletResponse> apiResponse = new ApiResponse<>(200, "OK", walletResponse);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{walletId}")
    public ResponseEntity<ApiResponse<WalletResponse>> deleteWallet(@PathVariable("walletId") Long walletId) throws Exception {
        WalletResponse walletResponse = walletService.deleteWallet(walletId);

        ApiResponse<WalletResponse> apiResponse = new ApiResponse<>(200, "OK", walletResponse);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
