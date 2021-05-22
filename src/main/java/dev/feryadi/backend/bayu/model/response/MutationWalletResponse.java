package dev.feryadi.backend.bayu.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MutationWalletResponse {
    private Long id;
    private String address;
}