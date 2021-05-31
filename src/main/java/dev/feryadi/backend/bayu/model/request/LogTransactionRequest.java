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
    private String originWalletAddress;
    private String destinationWalletAddress;
    private BigDecimal amount;
    private String description;
}
