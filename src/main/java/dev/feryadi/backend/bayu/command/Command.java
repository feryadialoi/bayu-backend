package dev.feryadi.backend.bayu.command;

import dev.feryadi.backend.bayu.model.request.command.CommandRequest;

public interface Command<RESPONSE, REQUEST extends CommandRequest> {
    RESPONSE execute(REQUEST request) throws Exception;
}
