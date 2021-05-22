package dev.feryadi.backend.bayu.model.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class MutationResponse {
    private MutationWalletResponse originWallet;
    private MutationWalletResponse destinationWallet;
    private BigDecimal amount;
    private String description;
    private LocalDateTime createdDate;
}
