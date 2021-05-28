package dev.feryadi.backend.bayu.commandimpl.user;

import dev.feryadi.backend.bayu.command.user.GetUsersCommand;
import dev.feryadi.backend.bayu.specificationandcriteria.SearchCriteria;
import dev.feryadi.backend.bayu.specificationandcriteria.SearchOperation;
import dev.feryadi.backend.bayu.entity.User;
import dev.feryadi.backend.bayu.entity.roleandpermission.Role;
import dev.feryadi.backend.bayu.model.request.ListUserRequest;
import dev.feryadi.backend.bayu.model.request.command.GetUsersCommandRequest;
import dev.feryadi.backend.bayu.model.response.UserResponse;
import dev.feryadi.backend.bayu.modelmapper.UserMapper;
import dev.feryadi.backend.bayu.repository.RoleRepository;
import dev.feryadi.backend.bayu.repository.UserRepository;
import dev.feryadi.backend.bayu.specificationandcriteria.GenericSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

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

        Sort sort = Sort.by("id").ascending();

        if (listUserRequest.getSort() != null) {
            String[] sortBy = listUserRequest.getSort().split("\\.");
            if (sortBy[1].equals("asc")) {
                sort = Sort.by(sortBy[0]).ascending();
            } else if (sortBy[1].equals("desc")) {
                sort = Sort.by(sortBy[0]).descending();
            }
        }

        PageRequest pageRequest = PageRequest.of(listUserRequest.getPage(), listUserRequest.getSize(), sort);

        GenericSpecification<User> userGenericSpecification = new GenericSpecification<>();

        if (listUserRequest.getRoleName() != null) {
            Optional<Role> roleByName = roleRepository.findRoleByName(listUserRequest.getRoleName());

            SearchCriteria roleNameCriteria = SearchCriteria.builder()
                    .key("role")
                    .operation(SearchOperation.EQUAL)
                    .value(roleByName.get())
                    .build();
            userGenericSpecification.add(roleNameCriteria);
        }

        if (listUserRequest.getName() != null) {
            SearchCriteria nameCriteria = SearchCriteria.builder()
                    .key("name")
                    .operation(SearchOperation.MATCH)
                    .value(listUserRequest.getName())
                    .build();
            userGenericSpecification.add(nameCriteria);
        }

        if (listUserRequest.getUsername() != null) {
            SearchCriteria usernameCriteria = SearchCriteria.builder()
                    .key("username")
                    .operation(SearchOperation.MATCH)
                    .value(listUserRequest.getUsername())
                    .build();
            userGenericSpecification.add(usernameCriteria);
        }

        if (listUserRequest.getEmail() != null) {
            SearchCriteria emailCriteria = SearchCriteria.builder()
                    .key("email")
                    .operation(SearchOperation.MATCH)
                    .value(listUserRequest.getEmail())
                    .build();
            userGenericSpecification.add(emailCriteria);
        }

        if (listUserRequest.getPhone() != null) {
            SearchCriteria phoneCriteria = SearchCriteria.builder()
                    .key("phone")
                    .operation(SearchOperation.EQUAL)
                    .value(listUserRequest.getPhone())
                    .build();
            userGenericSpecification.add(phoneCriteria);
        }

        Page<User> page = userRepository.findAll(userGenericSpecification, pageRequest);

        List<User> users = page.get().collect(Collectors.toList());

        return users.stream().map(userMapper::mapUserToUserResponse).collect(Collectors.toList());
    }
}
