package dev.feryadi.backend.bayu.domain.v1.wallet;

public class WalletResponse {
    public Long id;
    public String address;

    public WalletResponse(Long id, String address) {
        this.id = id;
        this.address = address;
    }
}
