package hu.geewine.studentsspringbootrest.persistence;

import hu.geewine.studentsspringbootrest.model.Student;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CustomStudentRepositoryImpl implements CustomStudentRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean findByProperties(Student student) {
        StringBuilder jpql = new StringBuilder();
        jpql.append("from Student where 1=1 ");

        Map<String, Object> parameters = new HashMap<>();

        if (StringUtils.hasLength(student.getName())) {
            jpql.append("and name = :name ");
            parameters.put("name", student.getName());
        }

        if (student.getAge() != null) {
            jpql.append("and age = :age ");
            parameters.put("age", student.getAge());
        }

        TypedQuery<Student> query = entityManager.createQuery(jpql.toString(), Student.class);

        parameters.forEach((key, value) -> query.setParameter(key, value));

        List<Student> results = query.getResultList();

        return results.size() > 0 ? false : true;
    }

}
