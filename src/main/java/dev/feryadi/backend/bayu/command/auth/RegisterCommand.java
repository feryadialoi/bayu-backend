package dev.feryadi.backend.bayu.command.auth;


import dev.feryadi.backend.bayu.command.FunctionCommand;
import dev.feryadi.backend.bayu.model.request.command.RegisterCommandRequest;
import dev.feryadi.backend.bayu.model.response.RegisterResponse;

public interface RegisterCommand extends FunctionCommand<RegisterResponse, RegisterCommandRequest> {
}
