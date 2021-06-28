package dev.feryadi.backend.bayu.command.auth;

import dev.feryadi.backend.bayu.command.FunctionCommand;
import dev.feryadi.backend.bayu.model.request.command.ChangePasswordCommandRequest;

public interface ChangePasswordCommand extends FunctionCommand<Void, ChangePasswordCommandRequest> {
}
