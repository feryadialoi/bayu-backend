package dev.feryadi.backend.bayu.domain.v1.wallet;

import javax.validation.constraints.NotBlank;

public class CreateWalletRequest {
    @NotBlank
    public String address;
}
