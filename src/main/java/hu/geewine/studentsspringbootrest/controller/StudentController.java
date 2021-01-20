package hu.geewine.studentsspringbootrest.controller;

import hu.geewine.studentsspringbootrest.dto.StudentV1;
import hu.geewine.studentsspringbootrest.dto.StudentV2;
import hu.geewine.studentsspringbootrest.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     *      API V1 CRUD operations
     */

    @GetMapping(value = "", produces = "application/vnd.gw.students.rest-v1+json")
    public List<StudentV1> retrieveAllStudentsV1() {
        return studentService.findAllV1();
    }

    @GetMapping(value = "/filter", produces = "application/vnd.gw.students.rest-v1+json")
    public List<StudentV1> searchStudentsV1(@RequestParam(value = "search") String search) {
        return studentService.searchStudentsV1(search);
    }

    @GetMapping(value = "/{id}", produces = "application/vnd.gw.students.rest-v1+json")
    public StudentV1 findStudentV1ById(@PathVariable long id) {
        return studentService.findStudentV1ById(id);
    }

    @PostMapping(value = "", produces = "application/vnd.gw.students.rest-v1+json")
    public ResponseEntity<Object> createStudent(@RequestBody StudentV1 studentV1) {
        StudentV1 savedStudent = studentService.saveV1(studentV1);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedStudent.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/{id}", produces = "application/vnd.gw.students.rest-v1+json")
    public ResponseEntity<Object> updateStudentV1(@RequestBody StudentV1 studentV1, @PathVariable long id) {
        studentService.updateStudentV1(studentV1, id);
        return ResponseEntity.noContent().build();
    }


    /**
     *      API V1, V2 common CRUD operations
     */

    @DeleteMapping(value = "/{id}", produces = {"application/vnd.gw.students.rest-v1+json", "application/vnd.gw.students.rest-v2+json"})
    public void deleteStudentById(@PathVariable long id) {
        studentService.deleteStudentById(id);
    }


    /**
     *      API V2 CRUD operations
     */

    @GetMapping(value = "",produces = "application/vnd.gw.students.rest-v2+json")
    public List<StudentV2> retrieveAllStudentsV2() {
        return studentService.findAllV2();
    }

    @GetMapping(value = "/filter", produces = "application/vnd.gw.students.rest-v2+json")
    public List<StudentV2> searchStudentsV2(@RequestParam(value = "search") String search) {
        return studentService.searchStudentsV2(search);
    }

    @GetMapping(value = "/{id}", produces = "application/vnd.gw.students.rest-v2+json")
    public StudentV2 findStudentV2ById(@PathVariable long id) {
        return studentService.findStudentV2ById(id);
    }

    @PostMapping(value = "",produces = "application/vnd.gw.students.rest-v2+json")
    public ResponseEntity<Object> createStudent(@RequestBody StudentV2 studentV2) {
        StudentV2 savedStudent = studentService.saveV2(studentV2);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedStudent.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/{id}", produces = "application/vnd.gw.students.rest-v2+json")
    public ResponseEntity<Object> updateStudentV2(@RequestBody StudentV2 studentV2, @PathVariable long id) {
        studentService.updateStudentV2(studentV2, id);
        return ResponseEntity.noContent().build();
    }

}
