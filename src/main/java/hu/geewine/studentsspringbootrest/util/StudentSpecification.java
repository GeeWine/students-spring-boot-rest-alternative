package hu.geewine.studentsspringbootrest.util;

import hu.geewine.studentsspringbootrest.model.Student;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.expression.common.LiteralExpression;

import javax.persistence.criteria.*;

public class StudentSpecification implements Specification<Student> {

    private SearchCriteria criteria;

    public StudentSpecification(final SearchCriteria criteria) {
        super();
        this.criteria = criteria;
    }

    public SearchCriteria getCriteria() {
        return criteria;
    }

    @Override
    public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if (criteria.getOperation().equalsIgnoreCase(">")) {
            return criteriaBuilder.greaterThanOrEqualTo(
                    root.<String> get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return criteriaBuilder.lessThanOrEqualTo(
                    root.<String> get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase(":")) {
            String originalKey = criteria.getKey();
            String alteredKey = originalKey;
            if (originalKey.equals("firstName") || originalKey.equals("lastName")) {
                alteredKey = "name";
            }
            if (root.get(alteredKey).getJavaType() == String.class) {
                if (originalKey.equals("firstName")) {
                    Expression<String> firstNameExp = criteriaBuilder.function("first_name", String.class, root.<String>get(alteredKey));
                    return criteriaBuilder.equal(
                            firstNameExp, criteria.getValue());
                } else if (originalKey.equals("lastName")) {
                    Expression<String> lastNameExp = criteriaBuilder.function("last_name", String.class, root.<String>get(alteredKey));
                    return criteriaBuilder.equal(
                            lastNameExp, criteria.getValue());
                } else {
                    return criteriaBuilder.like(
                            root.<String>get(alteredKey), "%" + criteria.getValue() + "%");
                }
            } else {
                return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
        return null;
    }
}
