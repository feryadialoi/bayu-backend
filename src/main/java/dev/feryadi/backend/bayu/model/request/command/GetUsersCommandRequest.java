package dev.feryadi.backend.bayu.model.request.command;

import dev.feryadi.backend.bayu.model.request.ListUserRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetUsersCommandRequest implements CommandRequest {
    private ListUserRequest listUserRequest;
}
