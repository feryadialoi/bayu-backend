package dev.feryadi.backend.bayu.model.request.command;

import dev.feryadi.backend.bayu.model.request.ForgetPasswordRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ForgetPasswordCommandRequest implements CommandRequest{
    private ForgetPasswordRequest forgetPasswordRequest;
}
