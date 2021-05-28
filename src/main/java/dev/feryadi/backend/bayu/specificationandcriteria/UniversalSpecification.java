package dev.feryadi.backend.bayu.specificationandcriteria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UniversalSpecification<T> implements Specification<T> {

    private SearchCriteria searchCriteria;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        SearchOperation searchOperation = searchCriteria.getOperation();
        String key = searchCriteria.getKey();
        Object value = searchCriteria.getValue();
        List<Object> values = searchCriteria.getValues();

        switch (searchOperation) {
            case GREATER_THAN:
                return criteriaBuilder.greaterThan(root.get(key), value.toString());
            case LESS_THAN:
                return criteriaBuilder.lessThan(root.get(key), value.toString());
            case GREATER_THAN_EQUAL:
                return criteriaBuilder.greaterThanOrEqualTo(root.get(key), value.toString());
            case LESS_THAN_EQUAL:
                return criteriaBuilder.lessThanOrEqualTo(root.get(key), value.toString());
            case NOT_EQUAL:
                return criteriaBuilder.notEqual(root.get(key), value.toString());
            case MATCH:
                return criteriaBuilder.like(criteriaBuilder.lower(root.get(key)), "%" + value.toString().toLowerCase() + "%");
            case MATCH_END:
                return criteriaBuilder.like(criteriaBuilder.lower(root.get(key)), value.toString().toLowerCase() + "%");
            case EQUAL:
                return criteriaBuilder.equal(root.get(key), value.toString());
            case IN:
                return root.get(key).in(values);
            default:
                throw new RuntimeException("not supported operation");
        }
    }
}
