package dev.feryadi.backend.bayu.specificationandcriteria.criteria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchCriteria {
    private String key;
    private SearchOperation operation;
    private Object value;
    private List<Object> values;

    public SearchCriteria(String key, SearchOperation operation, Object value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }

    public SearchCriteria(String key, SearchOperation operation, List<Object> values) {
        this.key = key;
        this.operation = operation;
        this.values = values;
    }
}
