package dev.feryadi.backend.bayu.model.request.command;

import dev.feryadi.backend.bayu.model.request.CreateUserPinRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserPinCommandRequest implements CommandRequest {
    private Long userId;
    private CreateUserPinRequest createUserPinRequest;
}
