package dev.feryadi.backend.bayu.service;

import dev.feryadi.backend.bayu.command.Command;
import dev.feryadi.backend.bayu.model.request.command.CommandRequest;

public interface ServiceExecutor {
    <RESPONSE, REQUEST extends CommandRequest> RESPONSE execute(Class<? extends Command<RESPONSE, REQUEST>> command, REQUEST request) throws Exception;
}
