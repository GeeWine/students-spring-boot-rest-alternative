package hu.geewine.studentsspringbootrest.model;

import javax.persistence.*;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long age;
    private String name;
    private Long grade;

    public Student() {
    }

    public Student(Long id, Long age, String name, Long grade) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.grade = grade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getGrade() { return grade; }

    public void setGrade(Long grade) { this.grade = grade; }

}
