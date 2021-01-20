package hu.geewine.studentsspringbootrest.service;

import hu.geewine.studentsspringbootrest.util.StudentDuplicationException;
import hu.geewine.studentsspringbootrest.util.StudentNotFoundException;
import hu.geewine.studentsspringbootrest.model.Student;
import hu.geewine.studentsspringbootrest.model.StudentMapper;
import hu.geewine.studentsspringbootrest.dto.StudentV1;
import hu.geewine.studentsspringbootrest.dto.StudentV2;
import hu.geewine.studentsspringbootrest.persistence.StudentRepository;
import hu.geewine.studentsspringbootrest.util.StudentSpecificationsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper studentMapper;

    public List<StudentV1> findAllV1() {
        return studentMapper.mapStudentsToV1(studentRepository.findAll());
    }

    public List<StudentV1> searchStudentsV1(String search) {
        StudentSpecificationsBuilder builder = new StudentSpecificationsBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }
        Specification<Student> spec = builder.build();
        return studentMapper.mapStudentsToV1(studentRepository.findAll(spec));
    }

    public StudentV1 findStudentV1ById(long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (!student.isPresent()) {
            throw new StudentNotFoundException("Student ID: " + id);
        }
        return studentMapper.mapStudentToV1(student.get());
    }

    public StudentV1 saveV1(StudentV1 studentV1) {
        boolean unique = studentRepository.findByProperties(studentMapper.mapStudentV1ToStudent(studentV1));
        if (unique) {
            Student savedEntity = studentRepository.save(studentMapper.mapStudentV1ToStudent(studentV1));
            return studentMapper.mapStudentToV1(savedEntity);
        } else {
            throw new StudentDuplicationException();
        }
    }

    public void updateStudentV1(StudentV1 studentV1, long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (!student.isPresent()) {
            throw new StudentNotFoundException("Student ID: " + id);
        }
        studentV1.setId(id);
        saveV1(studentV1);
    }



    public void deleteStudentById(long id) {
        studentRepository.deleteById(id);
    }



    public List<StudentV2> findAllV2() {
        return studentMapper.mapStudentsToV2(studentRepository.findAll());
    }

    public List<StudentV2> searchStudentsV2(String search) {
        StudentSpecificationsBuilder builder = new StudentSpecificationsBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }
        Specification<Student> spec = builder.build();
        return studentMapper.mapStudentsToV2(studentRepository.findAll(spec));
    }

    public StudentV2 findStudentV2ById(long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (!student.isPresent()) {
            throw new StudentNotFoundException("Student ID: " + id);
        }
        return studentMapper.mapStudentToV2(student.get());
    }

    public StudentV2 saveV2(StudentV2 studentV2) {
        boolean unique = studentRepository.findByProperties(studentMapper.mapStudentV2ToStudent(studentV2));
        if (unique) {
            Student savedEntity = studentRepository.save(studentMapper.mapStudentV2ToStudent(studentV2));
            return studentMapper.mapStudentToV2(savedEntity);
        } else {
            throw new StudentDuplicationException();
        }
    }

    public void updateStudentV2(StudentV2 studentV2, long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (!student.isPresent()) {
            throw new StudentNotFoundException("Student ID: " + id);
        }
        studentV2.setId(id);
        saveV2(studentV2);
    }

}
