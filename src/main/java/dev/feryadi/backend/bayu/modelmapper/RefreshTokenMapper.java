package dev.feryadi.backend.bayu.modelmapper;

import dev.feryadi.backend.bayu.model.response.RefreshTokenResponse;

public interface RefreshTokenMapper {
    RefreshTokenResponse mapTokenToRefreshTokenResponse(String token);
}
