package dev.feryadi.backend.bayu.model.request.command;

import dev.feryadi.backend.bayu.model.request.ChangePasswordRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChangePasswordCommandRequest implements CommandRequest {
    private Long userId;
    private ChangePasswordRequest changePasswordRequest;
}
