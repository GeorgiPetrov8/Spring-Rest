package softuni.demo.model;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "courses")
public class Course extends BaseEntity {
    private String courseName;
    private BigDecimal price;
    private Integer mounts;
    private boolean enabled;
    private Set<Student> students;

    public Course() {
    }

    public Course(String courseName, BigDecimal price, Integer mounts, boolean enabled) {
        this.courseName = courseName;
        this.price = price;
        this.mounts = mounts;
        this.enabled = enabled;
    }

    @Column(name = "course_name",
            nullable = false,
            unique = true)
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Column(nullable = false)
    @Positive
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(nullable = false)
    @Positive
    public Integer getMounts() {
        return mounts;
    }

    public void setMounts(Integer mounts) {
        this.mounts = mounts;
    }

    @Column(nullable = false)
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "orders", joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"))
    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
