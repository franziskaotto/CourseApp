package at.codersbay.courseapp.api.course.get;

import at.codersbay.courseapp.api.course.Course;
import at.codersbay.courseapp.api.course.CourseRepository;
import at.codersbay.courseapp.api.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses/")
public class GetCourseController {

    @Autowired
    private CourseRepository courseRepository;

    /**
     * Path: "localhost/8081/api/courses/"
     * This Method lists all Courses in the DB
     * @return returns a List with all Courses & a HTTPStatus
     */
    @GetMapping("/")
    public ResponseEntity<List<Course>> getAllCourses() {

        List<Course> courses = courseRepository.findAll();

        if (courses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(courses, HttpStatus.OK);


    }

    /**
     * Path: "localhost/8081/api/courses/{id}"
     * This method finds one Course by its Id (long) in the DB.
     * @param id The Pathvariable is the ID of the Course to be found in the DB
     * @return This method returns one Course with the Id from the Pathvariable and a HTTPStatus.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Course> getById(@PathVariable long id) {


        Optional<Course> optionalCourse = courseRepository.findById(id);

        if (!optionalCourse.isPresent()) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Course course = optionalCourse.get();

        return ResponseEntity.ok(course);
    }


}
