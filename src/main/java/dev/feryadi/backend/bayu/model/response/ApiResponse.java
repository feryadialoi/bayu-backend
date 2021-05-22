package dev.feryadi.backend.bayu.model.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<D> {
    private Integer status;
    private String message;
    private D data;
}
