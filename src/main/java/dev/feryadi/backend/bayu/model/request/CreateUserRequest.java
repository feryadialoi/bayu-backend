package dev.feryadi.backend.bayu.model.request;

import dev.feryadi.backend.bayu.validation.constraint.EmailUniqueConstraint;
import dev.feryadi.backend.bayu.validation.constraint.PasswordConfirmationMatchConstraint;
import dev.feryadi.backend.bayu.validation.constraint.UsernameUniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@PasswordConfirmationMatchConstraint
public class CreateUserRequest {

    @NotEmpty
    private String name;

    @NotEmpty
    @UsernameUniqueConstraint
    private String username;

    @NotEmpty
    @EmailUniqueConstraint
    @Email
    private String email;

    @NotEmpty
    private String phone;

    @NotEmpty
    @Min(value = 8)
    private String password;

    @NotEmpty
    private String confirmationPassword;

}
