package dev.feryadi.backend.bayu.command.usermutation;

import dev.feryadi.backend.bayu.command.Command;
import dev.feryadi.backend.bayu.model.request.GetUserMutationDetailCommandRequest;
import dev.feryadi.backend.bayu.model.response.UserMutationDetailResponse;

public interface GetUserMutationDetailCommand extends Command<UserMutationDetailResponse, GetUserMutationDetailCommandRequest> {
}
