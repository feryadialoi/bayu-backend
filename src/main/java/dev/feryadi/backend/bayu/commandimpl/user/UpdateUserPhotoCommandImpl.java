package dev.feryadi.backend.bayu.commandimpl.user;

import dev.feryadi.backend.bayu.command.user.UpdateUserPhotoCommand;
import dev.feryadi.backend.bayu.entity.User;
import dev.feryadi.backend.bayu.exception.UserNotFoundException;
import dev.feryadi.backend.bayu.model.request.command.UpdateUserPhotoCommandRequest;
import dev.feryadi.backend.bayu.repository.UserRepository;
import dev.feryadi.backend.bayu.utils.UserPhotoStorageUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Component
@AllArgsConstructor
public class UpdateUserPhotoCommandImpl implements UpdateUserPhotoCommand {

    private final UserRepository userRepository;
    private final UserPhotoStorageUtil userPhotoStorageUtil;

    @Override
    public String execute(UpdateUserPhotoCommandRequest request) {
        Long userId = request.getUserId();

        Optional<User> optionalUser = userRepository.findById(userId);

        if (!optionalUser.isPresent()) {
            throw new UserNotFoundException("user with id " + userId + " not found");
        }

        User user = optionalUser.get();

        MultipartFile photo = request.getPhoto();

        userPhotoStorageUtil.save(photo);

        user.setPhoto(photo.getOriginalFilename());

        userRepository.save(user);

        return photo.getOriginalFilename();

    }
}
