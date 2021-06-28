package dev.feryadi.backend.bayu.command.auth;

import dev.feryadi.backend.bayu.command.FunctionCommand;
import dev.feryadi.backend.bayu.model.request.command.RefreshTokenCommandRequest;
import dev.feryadi.backend.bayu.model.response.RefreshTokenResponse;

public interface RefreshTokenCommand extends FunctionCommand<RefreshTokenResponse, RefreshTokenCommandRequest> {
}
