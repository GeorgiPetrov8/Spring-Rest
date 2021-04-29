package softuni.demo.model.view;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;
import java.util.Set;

public class CourseViewModel {
    private Long id;
    private String courseName;
    private BigDecimal price;
    private Integer mounts;
    private boolean enabled;
    @JsonIgnore
    private Set<StudentViewModel> studentsViewModels;

    public CourseViewModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getMounts() {
        return mounts;
    }

    public void setMounts(Integer mounts) {
        this.mounts = mounts;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<StudentViewModel> getStudentsViewModels() {
        return studentsViewModels;
    }

    public void setStudentsViewModels(Set<StudentViewModel> studentsViewModels) {
        this.studentsViewModels = studentsViewModels;
    }
}
