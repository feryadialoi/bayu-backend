package dev.feryadi.backend.bayu.model.request;

import dev.feryadi.backend.bayu.validation.constraint.EmailUniqueConstraint;
import dev.feryadi.backend.bayu.validation.constraint.PasswordConfirmationMatchConstraint;
import dev.feryadi.backend.bayu.validation.constraint.UsernameUniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@PasswordConfirmationMatchConstraint
public class CreateUserRequest {

    @NotBlank
    private String name;

    @NotBlank
    @UsernameUniqueConstraint
    private String username;

    @NotBlank
    @EmailUniqueConstraint
    private String email;

    @NotBlank
    private String phone;

    @NotBlank
    private String password;

    @NotBlank
    private String confirmationPassword;

}
