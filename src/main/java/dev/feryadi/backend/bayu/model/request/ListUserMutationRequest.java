package dev.feryadi.backend.bayu.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListUserMutationRequest {
    private Pageable pageable;
    private String startDate;
    private String endDate;
}
