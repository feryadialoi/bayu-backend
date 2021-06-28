package dev.feryadi.backend.bayu.command.auth;

import dev.feryadi.backend.bayu.command.FunctionCommand;
import dev.feryadi.backend.bayu.model.request.command.ForgetPasswordCommandRequest;

public interface ForgetPasswordCommand extends FunctionCommand<Void, ForgetPasswordCommandRequest> {
}
