package dev.feryadi.backend.bayu.validation;

import dev.feryadi.backend.bayu.repository.UserRepository;
import dev.feryadi.backend.bayu.validation.constraint.UsernameUnique;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameUniqueConstraintValidator implements ConstraintValidator<UsernameUnique, String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        return !userRepository.existsByUsername(username);
    }
}
