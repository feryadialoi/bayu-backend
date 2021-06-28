package dev.feryadi.backend.bayu.validation.constraint;

import dev.feryadi.backend.bayu.validation.UsernameUniqueConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UsernameUniqueConstraintValidator.class)
public @interface UsernameUnique {
    String message() default "Username already taken";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
