package dev.feryadi.backend.bayu.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListUserRequest {
    private Integer size;
    private Integer page;

    private String sort;

    private String name;
    private String username;
    private String email;
    private String phone;

    private String roleName;
}
