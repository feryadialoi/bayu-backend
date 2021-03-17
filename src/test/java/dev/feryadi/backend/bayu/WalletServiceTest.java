package dev.feryadi.backend.bayu;

import dev.feryadi.backend.bayu.domain.v1.wallet.WalletResponse;
import dev.feryadi.backend.bayu.domain.v1.wallet.WalletService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration
@EnableJpaRepositories
public class WalletServiceTest {
    private WalletService walletService;

    @Autowired
    public WalletServiceTest(WalletService walletService) {
        this.walletService = walletService;
    }

    @Test
    public void testWalletServiceGetListOfWallet() {
        List<WalletResponse> listOfWalletResponse = walletService.getListOfWallet();

    }
}
