package dev.feryadi.backend.bayu.validation.constraint;

import dev.feryadi.backend.bayu.model.request.CreateUserRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordConfirmationMatchConstraintValidator implements ConstraintValidator<PasswordConfirmationMatchConstraint, CreateUserRequest> {
    @Override
    public boolean isValid(CreateUserRequest createUserRequest, ConstraintValidatorContext constraintValidatorContext) {
        return createUserRequest.getPassword().equals(createUserRequest.getConfirmationPassword());
    }
}
