package dev.feryadi.backend.bayu.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAuthorityRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String displayName;
    private String description = "";
}
