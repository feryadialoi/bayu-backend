package dev.feryadi.backend.bayu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotValidDetail {
    private String property;
    private String errorMessage;
}
