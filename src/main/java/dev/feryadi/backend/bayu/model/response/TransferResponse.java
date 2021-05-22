package dev.feryadi.backend.bayu.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferResponse {
    private WalletResponse originWallet;
    private WalletResponse destinationWallet;
    private BigDecimal amount;
    private String description;
    private LocalDateTime createdDate;
}
