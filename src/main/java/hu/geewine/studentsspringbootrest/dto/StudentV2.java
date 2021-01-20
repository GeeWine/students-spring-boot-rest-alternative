package hu.geewine.studentsspringbootrest.dto;

public class StudentV2 {

    private Long id;
    private Long age;
    private String firstName;
    private String lastName;

    public StudentV2() {
    }

    public StudentV2(Long id, Long age, String firstName, String lastName) {
        this.id = id;
        this.age = age;
        this.setFirstName(firstName);
        this.setLastName(lastName);
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
