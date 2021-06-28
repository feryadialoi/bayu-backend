package dev.feryadi.backend.bayu.command.usermutation;

import dev.feryadi.backend.bayu.command.FunctionCommand;
import dev.feryadi.backend.bayu.model.request.command.GetUserMutationsCommandRequest;
import dev.feryadi.backend.bayu.model.response.UserMutationResponse;

import java.util.List;

public interface GetUserMutationsCommand extends FunctionCommand<List<UserMutationResponse>, GetUserMutationsCommandRequest> {
}
