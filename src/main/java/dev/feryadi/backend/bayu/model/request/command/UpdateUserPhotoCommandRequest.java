package dev.feryadi.backend.bayu.model.request.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserPhotoCommandRequest implements CommandRequest {
    private Long userId;
    private MultipartFile photo;
}
