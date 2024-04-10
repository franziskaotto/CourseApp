package at.codersbay.courseapp.api.course.get;

import at.codersbay.courseapp.api.course.Course;
import at.codersbay.courseapp.api.course.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/courses/")
public class GetCourseController {

    @Autowired
    private CourseRepository courseRepository;


    //TODO commentar schreiben
    /**
     * Path: "localhost/8081/api/courses/"
     * This Method lists all
     * @return
     */
    @GetMapping("/")
    public ResponseEntity<List<Course>> getAllCourses() {

        List<Course> courses = courseRepository.findAll();

        if (courses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(courses, HttpStatus.OK);


    }


}
