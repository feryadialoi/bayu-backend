package dev.feryadi.backend.bayu.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotNull
    @NotBlank
    private String username;

    @NotBlank
    @NotBlank
    private String password;
}
