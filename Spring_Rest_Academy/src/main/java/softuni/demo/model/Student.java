package softuni.demo.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student extends BaseEntity {
    private String firstName;
    private String lastName;
    private Integer age;
    private Role role;
    private Set<Course> courses;

    public Student() {
    }

    public Student(String firstName, String lastName, Integer age, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.role = role;
    }

    @Column(name = "first_name", nullable = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name", nullable = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(nullable = false)
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "orders", joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"))
    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
