package at.codersbay.courseapp.api.course.delete;


import at.codersbay.courseapp.api.ResponseBody;
import at.codersbay.courseapp.api.course.Course;
import at.codersbay.courseapp.api.course.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/courses/")
public class deleteCourseController {

    @Autowired
    CourseRepository courseRepository;

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBody> deleteCourseById(@PathVariable long id) {

        //try to find the requestet Course to be deleted:
        Optional<Course> optionalCourse = courseRepository.findById(id);

        ResponseBody responseBody = new ResponseBody();

        if(optionalCourse.isEmpty()) {
            responseBody.addErrorMessage("could not delete Course by id: " + id);
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        } else {
            responseBody.addMessage("Course successfully deleted ");
            return new ResponseEntity<>(responseBody, HttpStatus.ACCEPTED);
        }
    }

    @DeleteMapping("/courseTitle/{title}")
    public ResponseEntity<ResponseBody> deleteCourseByTitle (@PathVariable String title) {

      Optional<Course> optionalCourse = this.courseRepository.findCourseByTitle(title);

      ResponseBody responseBody = new ResponseBody();

      if(optionalCourse.isEmpty()) {
          responseBody.addErrorMessage("Could not find the Course " + title);
          return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
      }

      Course course = optionalCourse.get();

      this.courseRepository.deleteById(course.getId());


      //check if delete really worked
        optionalCourse = courseRepository.findCourseByTitle(title);

        if(optionalCourse.isPresent()) {
            responseBody.addErrorMessage("Could not delete Course by Coursename: " + title);
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        } else {
            responseBody.addMessage("Course " + title +  " successfully deleted");
            return new ResponseEntity<>(responseBody, HttpStatus.ACCEPTED);
        }
    }




}
