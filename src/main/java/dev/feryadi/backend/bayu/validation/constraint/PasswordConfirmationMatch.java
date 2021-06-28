package dev.feryadi.backend.bayu.validation.constraint;

import dev.feryadi.backend.bayu.validation.RegisterPasswordConfirmationMatchConstraintValidator;
import dev.feryadi.backend.bayu.validation.UserPasswordConfirmationMatchConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {
        UserPasswordConfirmationMatchConstraintValidator.class,
        RegisterPasswordConfirmationMatchConstraintValidator.class,
})
public @interface PasswordConfirmationMatch {
    String message() default "Password not match";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
