package dev.feryadi.backend.bayu.command.usermutation;

import dev.feryadi.backend.bayu.command.Command;
import dev.feryadi.backend.bayu.model.request.command.GetUserMutationsCommandRequest;
import dev.feryadi.backend.bayu.model.response.UserMutationResponse;

import java.util.List;

public interface GetUserMutationsCommand extends Command<List<UserMutationResponse>, GetUserMutationsCommandRequest> {
}
