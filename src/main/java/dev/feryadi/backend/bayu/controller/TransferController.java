package dev.feryadi.backend.bayu.controller;

import dev.feryadi.backend.bayu.model.request.TransferRequest;
import dev.feryadi.backend.bayu.model.response.ApiResponse;
import dev.feryadi.backend.bayu.model.response.TransferResponse;
import dev.feryadi.backend.bayu.service.TransferService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TransferController extends BaseController {

    private final TransferService transferService;


    @PostMapping(value = {"/api/v1/transfers"})
    public ResponseEntity<ApiResponse<TransferResponse>> transfer(@RequestBody TransferRequest transferRequest) throws Exception {
        return createResponse(
                HttpStatus.OK,
                "Transfer successfully",
                transferService.transfer(transferRequest)
        );
    }
}
