package dev.feryadi.backend.bayu.domain.v1.wallet;

public class UpdateWalletRequest {
    public String address;

    public UpdateWalletRequest(String address) {
        this.address = address;
    }
}
