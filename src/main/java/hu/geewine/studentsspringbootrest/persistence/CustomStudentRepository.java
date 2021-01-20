package hu.geewine.studentsspringbootrest.persistence;

import hu.geewine.studentsspringbootrest.model.Student;

public interface CustomStudentRepository {

    boolean findByProperties(Student student);

}
