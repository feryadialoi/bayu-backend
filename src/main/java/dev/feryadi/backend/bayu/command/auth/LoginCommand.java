package dev.feryadi.backend.bayu.command.auth;


import dev.feryadi.backend.bayu.command.FunctionCommand;
import dev.feryadi.backend.bayu.model.request.command.LoginCommandRequest;
import dev.feryadi.backend.bayu.model.response.LoginResponse;

public interface LoginCommand extends FunctionCommand<LoginResponse, LoginCommandRequest> {
}
