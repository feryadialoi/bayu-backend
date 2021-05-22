package dev.feryadi.backend.bayu.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {
    private Long id;
    private WalletResponse wallet;
    private Boolean status;
    private BigDecimal amount;
}
