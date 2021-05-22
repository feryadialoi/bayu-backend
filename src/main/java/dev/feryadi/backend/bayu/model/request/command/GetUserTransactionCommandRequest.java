package dev.feryadi.backend.bayu.model.request.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetUserTransactionCommandRequest implements CommandRequest {
    private Long userId;
    private Long transactionId;
}
