package dev.feryadi.backend.bayu.service;

import dev.feryadi.backend.bayu.command.FunctionCommand;
import dev.feryadi.backend.bayu.command.SupplierCommand;
import dev.feryadi.backend.bayu.model.request.command.CommandRequest;

public interface ServiceExecutor {

    <RESPONSE, REQUEST extends CommandRequest> RESPONSE execute(
            Class<? extends FunctionCommand<RESPONSE, REQUEST>> commandClass,
            REQUEST request
    );

    <RESPONSE> RESPONSE execute(
            Class<? extends SupplierCommand<RESPONSE>> commandClass
    );

}
