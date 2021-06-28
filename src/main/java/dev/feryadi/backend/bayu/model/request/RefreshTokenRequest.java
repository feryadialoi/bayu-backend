package dev.feryadi.backend.bayu.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenRequest {
    @NotNull
    @NotEmpty
    private String accessToken;

    @NotNull
    @NotEmpty
    private String refreshToken;
}
