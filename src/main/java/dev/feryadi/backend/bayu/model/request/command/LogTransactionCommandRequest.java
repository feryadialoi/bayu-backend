package dev.feryadi.backend.bayu.model.request.command;

import dev.feryadi.backend.bayu.model.request.LogTransactionRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogTransactionCommandRequest implements CommandRequest{
    private LogTransactionRequest logTransactionRequest;
}
