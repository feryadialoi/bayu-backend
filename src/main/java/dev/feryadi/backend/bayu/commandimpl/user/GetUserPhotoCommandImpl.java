package dev.feryadi.backend.bayu.commandimpl.user;

import dev.feryadi.backend.bayu.command.user.GetUserPhotoCommand;
import dev.feryadi.backend.bayu.entity.User;
import dev.feryadi.backend.bayu.exception.UserNotFoundException;
import dev.feryadi.backend.bayu.model.request.command.GetUserPhotoCommandRequest;
import dev.feryadi.backend.bayu.repository.UserRepository;
import dev.feryadi.backend.bayu.utils.UserPhotoStorageUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GetUserPhotoCommandImpl implements GetUserPhotoCommand {

    private final UserRepository userRepository;
    private final UserPhotoStorageUtil userPhotoStorageUtil;
    private final Environment environment;

    @Override
    public Resource execute(GetUserPhotoCommandRequest request) {

        Long userId = request.getUserId();

        Optional<User> optionalUser = userRepository.findById(userId);

        if (!optionalUser.isPresent()) {
            throw new UserNotFoundException("user with id " + userId + " not found");
        }

        User user = optionalUser.get();

        if (user.getPhoto() == null) {
            return userPhotoStorageUtil.load(environment.getProperty("user-photos.dummy-user-photo"));
        }

        return userPhotoStorageUtil.load(user.getPhoto());

    }
}
