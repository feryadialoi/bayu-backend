package dev.feryadi.backend.bayu.model.request.command;

import dev.feryadi.backend.bayu.model.request.CreateUserRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserCommandRequest implements CommandRequest {
    private CreateUserRequest createUserRequest;
}
