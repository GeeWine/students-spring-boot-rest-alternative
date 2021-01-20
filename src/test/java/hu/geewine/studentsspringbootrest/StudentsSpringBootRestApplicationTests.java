package hu.geewine.studentsspringbootrest;

import hu.geewine.studentsspringbootrest.model.Student;
import hu.geewine.studentsspringbootrest.persistence.StudentRepository;
import hu.geewine.studentsspringbootrest.util.SearchCriteria;
import hu.geewine.studentsspringbootrest.util.StudentSpecification;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@Rollback
@SpringBootTest
public class StudentsSpringBootRestApplicationTests {

    @Autowired
    private StudentRepository studentRepository;

    private Student testOne;
    private Student testTwo;
    private Student testThree;

    @BeforeEach
    public void init() {
        testOne = new Student();
        testOne.setAge(21L);
        testOne.setName("Test One");
        testOne.setGrade(5L);
        studentRepository.save(testOne);
        testTwo = new Student();
        testTwo.setAge(24L);
        testTwo.setName("Test Two");
        testTwo.setGrade(6L);
        studentRepository.save(testTwo);
        testThree = new Student();
        testThree.setAge(22L);
        testThree.setName("Three");
        testThree.setGrade(7L);
        studentRepository.save(testThree);
    }

    @Test
    void filterAge() {
        StudentSpecification specification = new StudentSpecification((new SearchCriteria("age", ":", 21)));

        List<Student> students = studentRepository.findAll(specification);

        assertThat(testOne).isIn(students);
        assertThat(testTwo).isNotIn(students);
        assertThat(testThree).isNotIn(students);
    }

    @Test
    void filterName() {
        StudentSpecification specification = new StudentSpecification((new SearchCriteria("name", ":", "Test")));

        List<Student> students = studentRepository.findAll(specification);

        assertThat(testOne).isIn(students);
        assertThat(testTwo).isIn(students);
        assertThat(testThree).isNotIn(students);
    }

    @Test
    void filterGrade() {
        // > means greaterThanOrEqualTo, see StudentSpecification
        StudentSpecification specification = new StudentSpecification((new SearchCriteria("grade", ">", 6)));

        List<Student> students = studentRepository.findAll(specification);

        assertThat(testOne).isNotIn(students);
        assertThat(testTwo).isIn(students);
        assertThat(testThree).isIn(students);
    }

}
