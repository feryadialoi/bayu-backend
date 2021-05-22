package dev.feryadi.backend.bayu.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRoleResponse {
    private String name;
    private String username;
    private String email;
    private String phone;
    private RoleResponse role;
}
