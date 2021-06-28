package dev.feryadi.backend.bayu.command;

import dev.feryadi.backend.bayu.model.request.command.CommandRequest;

public interface FunctionCommand<RESPONSE, REQUEST extends CommandRequest> extends Command {
    RESPONSE execute(REQUEST request);
}