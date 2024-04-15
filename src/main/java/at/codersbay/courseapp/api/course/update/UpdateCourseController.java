package at.codersbay.courseapp.api.course.update;


import at.codersbay.courseapp.api.ResponseBody;
import at.codersbay.courseapp.api.course.Course;
import at.codersbay.courseapp.api.course.CourseRepository;
import at.codersbay.courseapp.api.course.CourseResponseBody;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/courses/")
public class UpdateCourseController {

    @Autowired
    CourseRepository courseRepository;


    /**
     * Path: "localhost/8081/api/courses/"
     * With this method a Course can be updated (patch).
     * @param updateCourseDTO The DTO contains all Key-Value Pairs.
     * @return This method returns the updated Course.
     */
    @PatchMapping
    public ResponseEntity<ResponseBody> updateCourse(@RequestBody UpdateCourseDTO updateCourseDTO) {

        if(updateCourseDTO == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<Course> optionalCourse = this.courseRepository.findById(updateCourseDTO.getId());

        CourseResponseBody courseResponseBody = new CourseResponseBody();
        if(optionalCourse.isEmpty()) {
            courseResponseBody.addErrorMessage("could not find the Course by ID: " + updateCourseDTO.getId());
            return new ResponseEntity<>(courseResponseBody, HttpStatus.BAD_REQUEST);
        }

        Course course = optionalCourse.get();

        if(!StringUtils.isEmpty(updateCourseDTO.getTitle())) {
            course.setTitle(updateCourseDTO.getTitle());
        }

        if(!StringUtils.isEmpty(updateCourseDTO.getDescription())) {
            course.setDescription(updateCourseDTO.getDescription());
        }

        if(!ObjectUtils.isEmpty(updateCourseDTO.getMaxParticipants())) {
            course.setMaxParticipants(updateCourseDTO.getMaxParticipants());
        }

        this.courseRepository.save(course);

        courseResponseBody.setCourse(course);

        return ResponseEntity.ok(courseResponseBody);


    }

}
