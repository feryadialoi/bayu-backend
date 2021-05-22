package dev.feryadi.backend.bayu.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignAuthoritiesToRoleRequest {
    private List<Long> authoritiesIds;
}
