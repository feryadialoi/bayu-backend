package dev.feryadi.backend.bayu.model.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserMutationResponse {
    private Long id;
    private MutationWalletResponse originWallet;
    private MutationWalletResponse destinationWallet;
    private BigDecimal amount;
    private String description;
    private BigDecimal initialBalance;
    private BigDecimal balance;
    private String type;
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;
}
