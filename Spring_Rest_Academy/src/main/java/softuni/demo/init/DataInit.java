package softuni.demo.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import softuni.demo.model.Course;
import softuni.demo.model.Role;
import softuni.demo.model.Student;
import softuni.demo.repository.CourseRepository;
import softuni.demo.repository.StudentRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Component
public class DataInit implements CommandLineRunner {
    private final StudentRepository studentRepository;

    @Autowired
    public DataInit(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (studentRepository.count() == 0) {
            this.saveStudents();
        }
    }

    private void saveStudents() {
        studentRepository.saveAll(initStudents());
    }

    private Set<Student> initStudents() {
        List<Course> courses = initCourses();

        Student georgiPetrov = new Student("Georgi", "Petrov", 23, Role.ADMIN);
        georgiPetrov.setCourses(Set.of(courses.get(3), courses.get(4)));
        Student antonTonchev = new Student("Anton", "Tonchev", 23, Role.ADMIN);
        antonTonchev.setCourses(Set.of(courses.get(2)));
        Student ivanVasilev = new Student("Ivan", "Vasilev", 25, Role.STUDENT);
        ivanVasilev.setCourses(Set.of(courses.get(1), courses.get(2)));
        Student peturTolev = new Student("Petur", "Tolev", 29, Role.STUDENT);
        peturTolev.setCourses(Set.of(courses.get(2), courses.get(3)));
        Student marioHulka = new Student("Mario", "Hulka", 31, Role.STUDENT);
        marioHulka.setCourses(Set.of(courses.get(1)));
        Student zdravkoMarkov = new Student("Zdravko", "Markov", 21, Role.STUDENT);
        zdravkoMarkov.setCourses(Set.of(courses.get(1), courses.get(2), courses.get(3)));

        return Set.of(georgiPetrov, antonTonchev, ivanVasilev, peturTolev, marioHulka, zdravkoMarkov);
    }


    private List<Course> initCourses() {
        return List.of(
                new Course("Java-Basics", BigDecimal.valueOf(100), 3, true),
                new Course("Java-Fundamentals", BigDecimal.valueOf(400), 5, true),
                new Course("Java-Advanced", BigDecimal.valueOf(500), 4, false),
                new Course("Java-DataBase", BigDecimal.valueOf(400), 4, true),
                new Course("Java-Web", BigDecimal.valueOf(450), 5, false)
        );
    }


}
