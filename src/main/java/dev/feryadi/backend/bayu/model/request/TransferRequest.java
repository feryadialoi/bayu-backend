package dev.feryadi.backend.bayu.model.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransferRequest {
    private String senderWalletAddress;
    private String receiverWalletAddress;
    private BigDecimal amount;
    private String description;
}
