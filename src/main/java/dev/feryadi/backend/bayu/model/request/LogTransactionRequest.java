package dev.feryadi.backend.bayu.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogTransactionRequest {
    private String senderWalletAddress;
    private String receiverWalletAddress;
    private BigDecimal amount;
    private String description;
    private Status status;
    private String statusDetail;

    public enum Status {
        SUCCESS, FAIL
    }
}
