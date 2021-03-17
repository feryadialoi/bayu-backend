package dev.feryadi.backend.bayu.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<D> {
    private final Integer status;
    private final String message;
    private final D data;

    public ApiResponse(Integer status, String message, D data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
