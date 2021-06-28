package dev.feryadi.backend.bayu.modelmapperimpl;

import dev.feryadi.backend.bayu.model.response.RefreshTokenResponse;
import dev.feryadi.backend.bayu.modelmapper.RefreshTokenMapper;
import org.springframework.stereotype.Component;

@Component
public class RefreshTokenMapperImpl implements RefreshTokenMapper {

    @Override
    public RefreshTokenResponse mapTokenToRefreshTokenResponse(String token) {
        return new RefreshTokenResponse(token);
    }
}
