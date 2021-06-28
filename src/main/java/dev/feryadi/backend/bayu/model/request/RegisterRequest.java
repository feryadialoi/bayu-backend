package dev.feryadi.backend.bayu.model.request;


import dev.feryadi.backend.bayu.validation.constraint.EmailUnique;
import dev.feryadi.backend.bayu.validation.constraint.PasswordConfirmationMatch;
import dev.feryadi.backend.bayu.validation.constraint.UsernameUnique;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@PasswordConfirmationMatch
public class RegisterRequest {
    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    @UsernameUnique
    private String username;

    @NotNull
    @NotEmpty
    @EmailUnique
    private String email;

    @NotNull
    @NotEmpty
    private String phone;

    @NotNull
    @NotEmpty
    @Size(min = 8)
    private String password;

    @NotNull
    @NotEmpty
    private String confirmPassword;
}
