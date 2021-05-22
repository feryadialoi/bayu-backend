package dev.feryadi.backend.bayu.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListUserMutationRequest {
    private Integer page;
    private Integer size;
    private String startDate;
    private String endDate;
}
