package dev.feryadi.backend.bayu.model.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponseError<E> {
    public Integer status;
    public String message;
    public E error;
}