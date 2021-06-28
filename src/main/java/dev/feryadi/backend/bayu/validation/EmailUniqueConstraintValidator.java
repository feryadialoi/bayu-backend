package dev.feryadi.backend.bayu.validation;

import dev.feryadi.backend.bayu.repository.UserRepository;
import dev.feryadi.backend.bayu.validation.constraint.EmailUnique;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailUniqueConstraintValidator implements ConstraintValidator<EmailUnique, String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        return !userRepository.existsByEmail(email);
    }
}
