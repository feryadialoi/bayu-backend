package dev.feryadi.backend.bayu.command.user;

import dev.feryadi.backend.bayu.entity.User;
import dev.feryadi.backend.bayu.model.request.command.GetUserCommandRequest;
import dev.feryadi.backend.bayu.model.response.UserResponse;
import dev.feryadi.backend.bayu.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class GetUserCommandTest {

    @Autowired
    private GetUserCommand getUserCommand;

    @MockBean
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        User user = User.builder()
                .id(1L)
                .name("Feryadi")
                .username("feryadialoi")
                .email("feryadialoi@gmail.com")
                .phone("081254798560")
                .password("password")
                .build();

        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.ofNullable(user));
    }

    @Test
    void execute() {
        UserResponse userResponse = getUserCommand.execute(new GetUserCommandRequest(1L));
        log.info(userResponse.toString());
        assertEquals(userResponse.getName(), "Feryadi");
    }
}