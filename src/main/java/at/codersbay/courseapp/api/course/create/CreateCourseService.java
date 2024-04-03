package at.codersbay.courseapp.api.course.create;


import at.codersbay.courseapp.api.course.Course;
import at.codersbay.courseapp.api.course.CourseRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCourseService {


    @Autowired
    private CourseRepository courseRepository;

    public Course createNewCourse(String title, String description, int maxParticipants) throws TitleIsEmptyException, DescriptionIsEmptyException, MaxParticipantsIsEmptyException{


        //check if all key value pairs are present
        if(StringUtils.isEmpty(title)) {
            throw new TitleIsEmptyException("the title cant be empty. Please enter a title");
        }else if(StringUtils.isEmpty(description)) {
            throw new DescriptionIsEmptyException("please enter a description");
        } else if (ObjectUtils.isEmpty(maxParticipants)) {
            throw new MaxParticipantsIsEmptyException("Please enter a max. number of participants");
        }

        //if everything is present, create new Course:
        Course course = new Course();
        course.setTitle(title);
        course.setDescription(description);
        course.setMaxParticipants(maxParticipants);

        return this.courseRepository.save(course);
    }
}
