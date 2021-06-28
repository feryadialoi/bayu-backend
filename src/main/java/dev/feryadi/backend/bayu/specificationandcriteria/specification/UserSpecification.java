package dev.feryadi.backend.bayu.specificationandcriteria.specification;

import dev.feryadi.backend.bayu.entity.User;
import dev.feryadi.backend.bayu.entity.roleandpermission.Role;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {
    public static Specification<User> emailIs(String email) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder
                .equal(root.get("email"), email);
    }

    public static Specification<User> phoneIs(String phone) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(
                root.get("phone"),
                phone
        );
    }

    public static Specification<User> nameIs(String name) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(
                root.get("name"),
                "%" + name + "%"
        );
    }

    public static Specification<User> usernameIs(String username) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(
                root.get("username"),
                username
        );
    }

    public static Specification<User> roleIs(Role role) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(
                root.get("role"),
                root
        );
    }
}
