package dev.feryadi.backend.bayu.model.request.command;

import dev.feryadi.backend.bayu.model.request.UpdateUserPinRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdatePinCommandRequest implements CommandRequest {
    private Long userId;
    private Long pinId;
    private UpdateUserPinRequest updateUserPinRequest;
}
