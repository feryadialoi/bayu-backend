package dev.feryadi.backend.bayu.specificationandcriteria;

import dev.feryadi.backend.bayu.specificationandcriteria.criteria.SearchCriteria;
import dev.feryadi.backend.bayu.specificationandcriteria.criteria.SearchOperation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
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
                return null;
        }
    }

    public enum Operator {
        AND, OR
    }

    public static <T> Specification<T> listSpecification(List<SearchCriteria> searchCriteriaList, Operator operator) {
        List<Predicate> predicates = new ArrayList<>();

        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                searchCriteriaList.forEach(searchCriteria -> {
                    SearchOperation searchOperation = searchCriteria.getOperation();
                    String key = searchCriteria.getKey();
                    Object value = searchCriteria.getValue();
                    List<Object> values = searchCriteria.getValues();

                    switch (searchOperation) {
                        case GREATER_THAN:
                            predicates.add(criteriaBuilder.greaterThan(root.get(key), value.toString()));
                            break;
                        case LESS_THAN:
                            predicates.add(criteriaBuilder.lessThan(root.get(key), value.toString()));
                            break;
                        case GREATER_THAN_EQUAL:
                            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(key), value.toString()));
                            break;
                        case LESS_THAN_EQUAL:
                            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(key), value.toString()));
                            break;
                        case NOT_EQUAL:
                            predicates.add(criteriaBuilder.notEqual(root.get(key), value.toString()));
                            break;
                        case MATCH:
                            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(key)), "%" + value.toString().toLowerCase() + "%"));
                            break;
                        case MATCH_END:
                            predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(key)), value.toString().toLowerCase() + "%"));
                            break;
                        case EQUAL:
                            predicates.add(criteriaBuilder.equal(root.get(key), value.toString()));
                            break;
                        case IN:
                            predicates.add(root.get(key).in(values));
                            break;
                    }
                });

                if (operator == Operator.AND) {
                    return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
                } else if (operator == Operator.OR) {
                    return criteriaBuilder.or(predicates.toArray(new Predicate[0]));
                } else {
                    throw new RuntimeException("not supported operator");
                }
            }
        };
    }
}
