package dev.feryadi.backend.bayu.exception;

import dev.feryadi.backend.bayu.model.NotValidDetail;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ValidationNotValidException extends ConstraintViolationException {

    public ValidationNotValidException(String message, Set<? extends ConstraintViolation<?>> constraintViolations) {
        super(message, constraintViolations);
    }

    public ValidationNotValidException(Set<? extends ConstraintViolation<?>> constraintViolations) {
        super(constraintViolations);
    }

    public List<NotValidDetail> getNotValidDetails() {

        Function<ConstraintViolation<?>, NotValidDetail> mapConstraintViolationToNotValidDetail = (ConstraintViolation<?> constraintViolation) -> {
            NotValidDetail notValidDetail = new NotValidDetail();
            notValidDetail.setProperty(constraintViolation.getPropertyPath().toString());
            notValidDetail.setErrorMessage(constraintViolation.getMessage());
            return notValidDetail;
        };

        return getConstraintViolations().stream()
                .map(mapConstraintViolationToNotValidDetail)
                .collect(Collectors.toList());
    }
}
