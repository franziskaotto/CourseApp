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

    @PostMapping
    public ResponseEntity<CourseResponseBody> createNewCourse (@RequestBody CreateCourseDTO createCourseDTO) throws TitleIsEmptyException, DescriptionIsEmptyException, MaxParticipantsIsEmptyException {

        if(createCourseDTO == null) {
            CourseResponseBody response = new CourseResponseBody();
            response.addErrorMessage("postbody is empty");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Course course = this.createCourseService.createNewCourse(createCourseDTO.getTitle(), createCourseDTO.getDescription(), createCourseDTO.getMaxParticipants());

        return new ResponseEntity<>(new CourseResponseBody(course), HttpStatus.OK);
    }
}
