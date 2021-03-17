package dev.feryadi.backend.bayu.domain.v1.wallet;

import javax.persistence.*;

@Entity(name = "wallets")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @Column(name = "address", unique = true)
    public String address;
}
