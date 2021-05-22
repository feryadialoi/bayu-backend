package dev.feryadi.backend.bayu.model.request.command;

import dev.feryadi.backend.bayu.model.request.UpdateUserRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserCommandRequest implements CommandRequest {
    private Long userId;
    private UpdateUserRequest updateUserRequest;
}
