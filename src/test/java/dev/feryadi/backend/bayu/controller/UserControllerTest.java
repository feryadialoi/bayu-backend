package dev.feryadi.backend.bayu.controller;

import dev.feryadi.backend.bayu.security.JwtUtil;
import dev.feryadi.backend.bayu.entity.User;
import dev.feryadi.backend.bayu.model.response.UserResponse;
import dev.feryadi.backend.bayu.repository.UserRepository;
import dev.feryadi.backend.bayu.service.UserService;
import dev.feryadi.backend.bayu.utils.ValidationUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private JwtUtil jwtUtil;
    @MockBean
    private PasswordEncoder passwordEncoder;
    @MockBean
    private UserDetailsService userDetailsService;
    @MockBean
    private ValidationUtil validationUtil;
    @MockBean
    private UserRepository userRepository;


    @BeforeEach
    void setUp() {
        UserResponse userResponse = UserResponse.builder()
                .id(1L)
                .name("Feryadi")
                .username("feryadialoi")
                .email("feryadialoi@gmail.com")
                .phone("081254798560")
                .build();

        Mockito.when(userService.getUser(1L)).thenReturn(userResponse);

        User user = User.builder()
                .id(1L)
                .name("admin")
                .username("admin")
                .password("password")
                .build();

        Mockito.when(userRepository.findByUsername("admin")).thenReturn(Optional.ofNullable(user));
    }

    @Test
    @Disabled
    void getUser() throws Exception {
        String token = "Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9" +
                "." +
                "eyJzdWIiOiJhZG1pbiIsInRva2VuSWQiOiJiNDI2MzE2Ny1mNDZhLTQwNjEtOGYwOS01YjdlNTNjMzY3ZmQiLCJleHAiOjE2MjI0ODQ5MTEsImlhdCI6MTYyMjQ4MTMxMSwidXNlcklkIjo0fQ" +
                "." +
                "ntG1Bc8PCs765E7_C2artBTHTzjh5yX5znPdL9XurnpXt1wivEUZhpWS1icBiwaNV6LTw0ucwQPAFQjU4Gorgg";

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/users/1")
                        .header(HttpHeaders.AUTHORIZATION, token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "    \"status\": 200,\n" +
                                "    \"message\": \"Get user successfully\",\n" +
                                "    \"data\": {\n" +
                                "        \"id\": 1,\n" +
                                "        \"name\": \"Feryadi\",\n" +
                                "        \"email\": \"feryadialoi@gmail.com\",\n" +
                                "        \"phone\": \"081254798560\",\n" +
                                "        \"username\": \"feryadialoi\"\n" +
                                "    }\n" +
                                "}")
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }
}