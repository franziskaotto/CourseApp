package at.codersbay.courseapp.data;

import at.codersbay.courseapp.api.course.Course;
import at.codersbay.courseapp.api.course.CourseRepository;
import at.codersbay.courseapp.api.student.Student;
import at.codersbay.courseapp.api.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class DataInitializer {
    @Autowired
    private StudentRepository studentRepository;

    //BookingRepository

    //CourseRepository
    @Autowired
    private CourseRepository courseRepository;

    @PostConstruct
    public void setup(){createInitialData();}


    public void createInitialData(){

        Student max = new Student();
        max.setUserName("MaxFirstUser");
        max.setFirstName("Max");
        max.setLastName("Musterman");
        max.seteMail("Max@mustermann.at");
        studentRepository.save(max);

        Student alex = new Student();
        alex.setUserName("AlexUser");
        alex.setFirstName("Alex");
        alex.setLastName("Blue");
        alex.seteMail("alexBlue@email.at");
        studentRepository.save(alex);

        Student leah = new Student();
        leah.setUserName("LeahUser");
        leah.setFirstName("Leah");
        leah.setLastName("Albrecht");
        leah.seteMail("leah@albrecht.at");
        studentRepository.save(leah);

        //COURSE - angelehnt an die USI-Sportkurse von der Uni
        Course swimming = new Course();
        swimming.setTitle("Swimming for Beginners");
        swimming.setDescription("If you want to learn how to swimm, this course is for you. We will start with the absolut basics");
        swimming.setMaxParticipants(3);
        courseRepository.save(swimming);

        Course athletics = new Course();
        athletics.setTitle("Athletics Basics");
        athletics.setDescription("Start running with us!");
        athletics.setMaxParticipants(5);
        courseRepository.save(athletics);

        Course gymnastics = new Course();
        gymnastics.setTitle("Gymnastics Advanced");
        gymnastics.setDescription("You are already a level B gymnast? Then feel free to join our advanced gymnastic course ");
        gymnastics.setMaxParticipants(9);
        courseRepository.save(gymnastics);

    }
}
