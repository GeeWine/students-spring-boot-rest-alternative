package hu.geewine.studentsspringbootrest.model;

import hu.geewine.studentsspringbootrest.dto.StudentV1;
import hu.geewine.studentsspringbootrest.dto.StudentV2;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentMapper {

    public StudentV1 mapStudentToV1(Student student) {
        return new StudentV1(student.getId(), student.getAge(), student.getName(), student.getGrade());
    }

    public Student mapStudentV1ToStudent(StudentV1 studentV1) {
        return new Student(studentV1.getId(), studentV1.getAge(), studentV1.getName(), studentV1.getGrade());
    }

    public StudentV2 mapStudentToV2(Student student) {
        String name = student.getName();
        String[] nameParts = name.split(" ");
        String lastName = nameParts[nameParts.length - 1];
        String firstName = name.substring(0, name.length() - lastName.length() - 1);
        return new StudentV2(student.getId(), student.getAge(), firstName, lastName);
    }

    public Student mapStudentV2ToStudent(StudentV2 studentV2) {
        return new Student(studentV2.getId(), studentV2.getAge(), studentV2.getFirstName() + " " + studentV2.getLastName(), null);
    }

    public List<StudentV1> mapStudentsToV1(List<Student> all) {
        List<StudentV1> result = new ArrayList<>();
        for (Student student : all) {
            result.add(mapStudentToV1(student));
        }
        return result;
    }

    public List<StudentV2> mapStudentsToV2(List<Student> all) {
        List<StudentV2> result = new ArrayList<>();
        for (Student student : all) {
            result.add(mapStudentToV2(student));
        }
        return result;
    }

}
