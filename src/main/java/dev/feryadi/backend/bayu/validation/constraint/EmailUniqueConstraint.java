package dev.feryadi.backend.bayu.validation.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = EmailUniqueConstraintValidator.class)
public @interface EmailUniqueConstraint {
    String message() default "Email already taken";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
