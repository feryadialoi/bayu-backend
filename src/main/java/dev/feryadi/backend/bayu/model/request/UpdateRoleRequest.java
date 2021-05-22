package dev.feryadi.backend.bayu.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRoleRequest {
    private String name;
    private String displayName;
    private String description;
}
