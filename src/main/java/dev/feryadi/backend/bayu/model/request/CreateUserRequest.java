package dev.feryadi.backend.bayu.model.request;

import dev.feryadi.backend.bayu.validation.constraint.EmailUnique;
import dev.feryadi.backend.bayu.validation.constraint.PasswordConfirmationMatch;
import dev.feryadi.backend.bayu.validation.constraint.UsernameUnique;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@PasswordConfirmationMatch
public class CreateUserRequest {

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    @UsernameUnique
    private String username;

    @NotEmpty
    @EmailUnique
    @Email
    private String email;

    @NotNull
    @NotEmpty
    private String phone;

    @NotEmpty
    @Size(min = 8)
    private String password;

    @NotNull
    @NotEmpty
    private String confirmPassword;

}
