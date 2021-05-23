package dev.feryadi.backend.bayu.model.request;

import dev.feryadi.backend.bayu.model.request.command.CommandRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetUserMutationDetailCommandRequest implements CommandRequest {
    private Long userId;
    private Long mutationId;
}
