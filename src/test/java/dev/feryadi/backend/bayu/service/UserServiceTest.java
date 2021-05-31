package dev.feryadi.backend.bayu.service;

import dev.feryadi.backend.bayu.command.user.GetUserCommand;
import dev.feryadi.backend.bayu.model.request.command.GetUserCommandRequest;
import dev.feryadi.backend.bayu.model.response.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private GetUserCommand getUserCommand;

    @BeforeEach
    void setup() {
        UserResponse userResponse = UserResponse.builder()
                .id(1L)
                .name("Feryadi")
                .username("feryadialoi")
                .email("feryadialoi@gmail.com")
                .phone("081254798560")
                .build();

        Mockito.when(getUserCommand.execute(new GetUserCommandRequest(1L))).thenReturn(userResponse);
    }

    @Test
    void getUser() {
        UserResponse user = userService.getUser(1L);
        log.info(user.toString());
        assertEquals(user.getName(), "Feryadi");
    }

}