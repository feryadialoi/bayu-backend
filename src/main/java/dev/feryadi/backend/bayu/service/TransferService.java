package dev.feryadi.backend.bayu.service;

import dev.feryadi.backend.bayu.model.request.TransferRequest;
import dev.feryadi.backend.bayu.model.response.TransferResponse;

public interface TransferService {
    TransferResponse transfer(TransferRequest transferRequest) throws Exception;
}
