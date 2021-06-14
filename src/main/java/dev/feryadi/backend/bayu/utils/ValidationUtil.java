package dev.feryadi.backend.bayu.utils;

import dev.feryadi.backend.bayu.exception.ValidationNotValidException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;


@Component
@AllArgsConstructor
public class ValidationUtil {

    private final Validator validator;

    public void validate(Object object) {
        Set<ConstraintViolation<Object>> validateResult = validator.validate(object);

        if (validateResult.size() != 0){
            throw new ValidationNotValidException(validateResult);
        }
    }
}
