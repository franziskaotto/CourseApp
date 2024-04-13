package at.codersbay.courseapp.api.course.create;


import at.codersbay.courseapp.api.course.Course;
import at.codersbay.courseapp.api.course.CourseResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/courses/")
public class CreateCourseController {

   @Autowired
   CreateCourseService createCourseService;


    /**
     * Path: "localhost/8081/api/courses/"
     * This method creates a new course. If one of the Key-Value Pairs in the DTO is left empty, the listed down below Exceptions is thrown.
     * @param createCourseDTO contains title (String), description (String) and maxParticipants (long) of the new course.
     * @return the new created Course & HTTPStatus.
     * @throws TitleIsEmptyException extends Exception. If the title of the course is left empty, the TitleIsLeftEmptyException is thrown
     * @throws DescriptionIsEmptyException extends Exception.If the Description of the Course is left empty, the TitleIsEmptyException is thrown
     * @throws MaxParticipantsIsEmptyException extends Exception. If the number of max. Participants is empty, the MaxParticipantsIsEmptyException is thrown
     */
    @PostMapping
    public ResponseEntity<CourseResponseBody> createNewCourse (@RequestBody CreateCourseDTO createCourseDTO) throws TitleIsEmptyException, DescriptionIsEmptyException, MaxParticipantsIsEmptyException {

        if(createCourseDTO == null) {
            CourseResponseBody response = new CourseResponseBody();
            response.addErrorMessage("Postbody is empty");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Course course = this.createCourseService.createNewCourse(createCourseDTO.getTitle(), createCourseDTO.getDescription(), createCourseDTO.getMaxParticipants());

        return new ResponseEntity<>(new CourseResponseBody(course), HttpStatus.OK);
    }
}
