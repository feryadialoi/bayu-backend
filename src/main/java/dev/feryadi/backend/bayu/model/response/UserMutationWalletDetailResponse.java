package dev.feryadi.backend.bayu.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserMutationWalletDetailResponse {
    private Long id;
    private String address;
    private UserMutationWalletUserDetailResponse user;
}
