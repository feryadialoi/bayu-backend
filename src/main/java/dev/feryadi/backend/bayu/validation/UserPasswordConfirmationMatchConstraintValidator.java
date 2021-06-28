package dev.feryadi.backend.bayu.validation;

import dev.feryadi.backend.bayu.model.request.CreateUserRequest;
import dev.feryadi.backend.bayu.validation.constraint.PasswordConfirmationMatch;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserPasswordConfirmationMatchConstraintValidator implements ConstraintValidator<PasswordConfirmationMatch, CreateUserRequest> {
    @Override
    public boolean isValid(CreateUserRequest createUserRequest, ConstraintValidatorContext constraintValidatorContext) {
        return createUserRequest.getPassword().equals(createUserRequest.getConfirmPassword());
    }
}
