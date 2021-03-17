package dev.feryadi.backend.bayu.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponseError<E> {
    public Integer status;
    public String message;
    public E error;

    public ApiResponseError(Integer status, String message, E error) {
        this.status = status;
        this.message = message;
        this.error = error;
    }
}