package dev.feryadi.backend.bayu.controller;

import dev.feryadi.backend.bayu.model.response.ApiResponse;
import dev.feryadi.backend.bayu.model.response.TransactionResponse;
import dev.feryadi.backend.bayu.service.TransactionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
public class TransactionController extends BaseController {

    private final TransactionService transactionService;

    @GetMapping(value = {"api/v1/transactions"}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ApiResponse<List<TransactionResponse>>> getTransactions() {
        return createResponse(
                HttpStatus.OK,
                "get transactions success",
                transactionService.getTransactions()
        );
    }
}
