package dev.feryadi.backend.bayu.controller;

import dev.feryadi.backend.bayu.model.request.ListUserMutationRequest;
import dev.feryadi.backend.bayu.model.response.ApiResponse;
import dev.feryadi.backend.bayu.model.response.UserMutationDetailResponse;
import dev.feryadi.backend.bayu.model.response.UserMutationResponse;
import dev.feryadi.backend.bayu.service.UserMutationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserMutationController extends BaseController {

    private final UserMutationService userMutationService;

    @PreAuthorize("hasRole('ADMIN') or @userSecurity.hasUserId(authentication, #userId)")
    @GetMapping(value = "/api/v1/users/{userId}/mutations")
    public ResponseEntity<ApiResponse<List<UserMutationResponse>>> getUserMutations(
            Pageable pageable,
            @PathVariable(value = "userId") Long userId,
            @RequestParam(value = "startDate", required = false) String startDate,
            @RequestParam(value = "endDate", required = false) String endDate
    ) {
        ListUserMutationRequest listUserMutationRequest = ListUserMutationRequest.builder()
                .pageable(pageable)
                .startDate(startDate)
                .endDate(endDate)
                .build();

        return createResponse(
                "Get user mutations successfully",
                userMutationService.getUserMutations(userId, listUserMutationRequest)
        );
    }

    @PreAuthorize("hasRole('ADMIN') or @userSecurity.hasUserId(authentication, #userId)")
    @GetMapping(value = "/api/v1/users/{userId}/mutations/{mutationId}")
    public ResponseEntity<ApiResponse<UserMutationDetailResponse>> getUserMutation(
            @PathVariable(value = "userId") Long userId,
            @PathVariable(value = "mutationId") Long mutationId
    ) {
        return createResponse(
                "Get user mutation successfully",
                userMutationService.getUserMutation(userId, mutationId)
        );
    }
}
