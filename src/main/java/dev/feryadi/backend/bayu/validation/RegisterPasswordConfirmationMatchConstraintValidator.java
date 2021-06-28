package dev.feryadi.backend.bayu.validation;

import dev.feryadi.backend.bayu.model.request.RegisterRequest;
import dev.feryadi.backend.bayu.validation.constraint.PasswordConfirmationMatch;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RegisterPasswordConfirmationMatchConstraintValidator implements ConstraintValidator<PasswordConfirmationMatch, RegisterRequest> {
    @Override
    public boolean isValid(RegisterRequest registerRequest, ConstraintValidatorContext constraintValidatorContext) {
        return registerRequest.getPassword().equals(registerRequest.getConfirmPassword());
    }
}
