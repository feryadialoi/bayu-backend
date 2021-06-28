package dev.feryadi.backend.bayu.validation.constraint;

import dev.feryadi.backend.bayu.validation.EmailUniqueConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = EmailUniqueConstraintValidator.class)
public @interface EmailUnique {
    String message() default "Email already taken";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
