package dev.feryadi.backend.bayu.model.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferRequest {
    private String originWalletAddress;
    private String destinationWalletAddress;
    private BigDecimal amount;
    private String description;
}
