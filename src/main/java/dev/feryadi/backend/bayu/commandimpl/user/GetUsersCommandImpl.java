package dev.feryadi.backend.bayu.commandimpl.user;

import dev.feryadi.backend.bayu.command.user.GetUsersCommand;
import dev.feryadi.backend.bayu.specificationandcriteria.criteria.SearchCriteria;
import dev.feryadi.backend.bayu.specificationandcriteria.criteria.SearchOperation;
import dev.feryadi.backend.bayu.entity.User;
import dev.feryadi.backend.bayu.entity.roleandpermission.Role;
import dev.feryadi.backend.bayu.model.request.ListUserRequest;
import dev.feryadi.backend.bayu.model.request.command.GetUsersCommandRequest;
import dev.feryadi.backend.bayu.model.response.UserResponse;
import dev.feryadi.backend.bayu.modelmapper.UserMapper;
import dev.feryadi.backend.bayu.repository.RoleRepository;
import dev.feryadi.backend.bayu.repository.UserRepository;
import dev.feryadi.backend.bayu.specificationandcriteria.UniversalSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class GetUsersCommandImpl implements GetUsersCommand {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserResponse> execute(GetUsersCommandRequest getUsersCommandRequest) {
        ListUserRequest listUserRequest = getUsersCommandRequest.getListUserRequest();

        List<SearchCriteria> searchCriteriaList = new ArrayList<>();
        if (listUserRequest.getRoleName() != null) {
            Optional<Role> roleByName = roleRepository.findRoleByName(listUserRequest.getRoleName());

            SearchCriteria roleNameCriteria = SearchCriteria.builder()
                    .key("role")
                    .operation(SearchOperation.EQUAL)
                    .value(roleByName.get())
                    .build();
            searchCriteriaList.add(roleNameCriteria);
        }

        if (listUserRequest.getName() != null) {
            SearchCriteria nameCriteria = SearchCriteria.builder()
                    .key("name")
                    .operation(SearchOperation.MATCH)
                    .value(listUserRequest.getName())
                    .build();
            searchCriteriaList.add(nameCriteria);
        }

        if (listUserRequest.getUsername() != null) {
            SearchCriteria usernameCriteria = SearchCriteria.builder()
                    .key("username")
                    .operation(SearchOperation.MATCH)
                    .value(listUserRequest.getUsername())
                    .build();
            searchCriteriaList.add(usernameCriteria);
        }

        if (listUserRequest.getEmail() != null) {
            SearchCriteria emailCriteria = SearchCriteria.builder()
                    .key("email")
                    .operation(SearchOperation.MATCH)
                    .value(listUserRequest.getEmail())
                    .build();
            searchCriteriaList.add(emailCriteria);
        }

        if (listUserRequest.getPhone() != null) {
            SearchCriteria phoneCriteria = SearchCriteria.builder()
                    .key("phone")
                    .operation(SearchOperation.EQUAL)
                    .value(listUserRequest.getPhone())
                    .build();
            searchCriteriaList.add(phoneCriteria);
        }

        Specification<User> specification = UniversalSpecification.<User>listSpecification(searchCriteriaList, UniversalSpecification.Operator.AND);

        Page<User> page = userRepository.findAll(specification, listUserRequest.getPageable());

        List<User> users = page.get().collect(Collectors.toList());

        return users.stream().map(userMapper::mapUserToUserResponse).collect(Collectors.toList());
    }
}
