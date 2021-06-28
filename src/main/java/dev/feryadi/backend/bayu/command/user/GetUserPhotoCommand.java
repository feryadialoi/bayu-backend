package dev.feryadi.backend.bayu.command.user;

import dev.feryadi.backend.bayu.command.FunctionCommand;
import dev.feryadi.backend.bayu.model.request.command.GetUserPhotoCommandRequest;
import org.springframework.core.io.Resource;

public interface GetUserPhotoCommand extends FunctionCommand<Resource, GetUserPhotoCommandRequest> {
}
