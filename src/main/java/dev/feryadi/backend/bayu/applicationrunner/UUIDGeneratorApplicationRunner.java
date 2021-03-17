package dev.feryadi.backend.bayu.applicationrunner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UUIDGeneratorApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        for (int i = 0; i < 10; i++) {
            System.out.println("UUID " + (i + 1) + " : \"" + UUID.randomUUID().toString() + "\"");
        }
    }
}
