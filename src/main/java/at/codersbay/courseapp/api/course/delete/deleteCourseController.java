package at.codersbay.courseapp.api.course.delete;


import at.codersbay.courseapp.api.ResponseBody;
import at.codersbay.courseapp.api.booking.Booking;
import at.codersbay.courseapp.api.course.Course;
import at.codersbay.courseapp.api.course.CourseRepository;
import at.codersbay.courseapp.api.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Optional;

@RestController
@RequestMapping("/api/courses/")
public class deleteCourseController {

    @Autowired
    CourseRepository courseRepository;

    /**
     * Path: "localhost/8081/api/courses/{id}"
     * This Method deletes an existing Course from the DB via its ID.
     * @param id Parameter id (long) is the ID of the to be deleted Course.
     * @return The ResponseBody contains a Message (String) and a HTTPStatus.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBody> deleteCourseById(@PathVariable long id) {

        ResponseBody responseBody = new ResponseBody();

        try {
            Optional<Course> optionalCourse = this.courseRepository.findById(id);


            courseRepository.deleteById(id);

            optionalCourse = courseRepository.findById(id);

            if(optionalCourse.isPresent()){
                responseBody.addErrorMessage("delete of Course failed. ID:  " + id);
                return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
            } else {
                responseBody.addMessage("Delete successful");
                return new ResponseEntity<>(responseBody, HttpStatus.OK);
            }
        } catch (EmptyResultDataAccessException erde) {
            responseBody.addErrorMessage("No Course" + id + " available");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);


        }

    }

}
