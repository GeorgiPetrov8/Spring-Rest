package softuni.demo.web;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import softuni.demo.model.Role;
import softuni.demo.model.Student;
import softuni.demo.model.view.CourseViewModel;
import softuni.demo.model.view.StudentViewModel;
import softuni.demo.repository.CourseRepository;
import softuni.demo.repository.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final ModelMapper mapper;

    @Autowired
    public StudentController(StudentRepository studentRepository, CourseRepository courseRepository, ModelMapper mapper) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.mapper = mapper;
    }

    @GetMapping
    public List<StudentViewModel> getStudent() {
        return studentRepository.findAll()
                .stream()
                .map(student -> {
                            Set<CourseViewModel> courseViewModels = student.getCourses().stream()
                                    .map(course -> mapper.map(course, CourseViewModel.class))
                                    .collect(Collectors.toSet());

                            StudentViewModel studentViewModel = mapper
                                    .map(student, StudentViewModel.class);

                            studentViewModel.setCoursesViewModels(courseViewModels);
                            return studentViewModel;
                        }
                ).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<StudentViewModel>> getStudentById(@PathVariable Long id) {
        StudentViewModel studentViewModel = studentRepository.findById(id)
                .map(student -> {
                    Set<CourseViewModel> courseViewModels = student.getCourses().stream()
                            .map(course -> mapper.map(course, CourseViewModel.class))
                            .collect(Collectors.toSet());

                    StudentViewModel studentView = mapper.map(student, StudentViewModel.class);
                    studentView.setCoursesViewModels(courseViewModels);
                    return studentView;
                }).orElse(null);

        if (studentViewModel == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(
                EntityModel.of(studentViewModel, createStudentLinks(studentViewModel)));
    }

    private Link[] createStudentLinks(StudentViewModel studentViewModel) {
        List<Link> result = new ArrayList<>();

        Link selfLink = linkTo(methodOn(StudentController.class).
                getStudentById(studentViewModel.getId())).withSelfRel();
        result.add(selfLink);

        Link studentCoursesLink = linkTo(methodOn(StudentController.class)
        .getCoursesByStudentId(studentViewModel.getId())).withRel("courses");
        result.add(studentCoursesLink);

        return result.toArray(new Link[0]);
    }

    @GetMapping("/{id}/courses")
    public ResponseEntity<CollectionModel<EntityModel<CourseViewModel>>>
                        getCoursesByStudentId(@PathVariable Long id) {
        Student student = studentRepository.findById(id).orElseThrow();

        Set<EntityModel<CourseViewModel>> entityModels = student.getCourses()
                .stream()
                .map(course -> mapper.map(course, CourseViewModel.class))
                .map(EntityModel::of)
                .collect(Collectors.toSet());

        return ResponseEntity.ok(CollectionModel.of(entityModels));
    }


    @PostMapping
    public ResponseEntity<StudentViewModel> saveStudent(@RequestBody StudentViewModel studentViewModel,
                                                        UriComponentsBuilder ucb) {

        Student student = mapper.map(studentViewModel, Student.class);
        student.setRole(Role.STUDENT);

        studentRepository.save(student);

        return ResponseEntity.created(
                ucb.path("students/{id}").buildAndExpand(student.getId()).toUri()
        ).build();
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentRepository.deleteById(id);
    }
}
