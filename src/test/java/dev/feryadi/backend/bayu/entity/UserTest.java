package dev.feryadi.backend.bayu.entity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTest {
    @Test
    public void test() {
        User user = new User();
        System.out.println(user);
    }
}
