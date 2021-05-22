package dev.feryadi.backend.bayu.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BalanceResponse {
    private Long id;
    private BigDecimal balance;
    private BigDecimal initialBalance;
}
