package hu.geewine.studentsspringbootrest.util;

import hu.geewine.studentsspringbootrest.model.Student;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StudentSpecificationsBuilder {

    private final List<SearchCriteria> params;

    public StudentSpecificationsBuilder() {
        params = new ArrayList<SearchCriteria>();
    }

    public StudentSpecificationsBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<Student> build() {
        if (params.size() == 0) {
            return null;
        }

        List<Specification> specs = params.stream()
                .map(StudentSpecification::new)
                .collect(Collectors.toList());

        Specification result = specs.get(0);

        for (int i = 1; i < params.size(); i++) {
            result = Specification.where(result).and(specs.get(i));
        }
        return result;
    }

}
