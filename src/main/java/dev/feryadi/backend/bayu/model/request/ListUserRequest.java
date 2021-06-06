package dev.feryadi.backend.bayu.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListUserRequest {
    private Pageable pageable;

    private String name;
    private String username;
    private String email;
    private String phone;

    private String roleName;
}
