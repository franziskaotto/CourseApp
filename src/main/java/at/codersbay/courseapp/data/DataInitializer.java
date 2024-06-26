package at.codersbay.courseapp.data;

import at.codersbay.courseapp.api.booking.Booking;
import at.codersbay.courseapp.api.booking.BookingID;
import at.codersbay.courseapp.api.booking.BookingRepository;
import at.codersbay.courseapp.api.course.Course;
import at.codersbay.courseapp.api.course.CourseRepository;
import at.codersbay.courseapp.api.student.Student;
import at.codersbay.courseapp.api.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;



@Service
public class DataInitializer {
    @Autowired
    private StudentRepository studentRepository;

    //BookingRepository
    @Autowired
    private BookingRepository bookingRepository;

    //CourseRepository
    @Autowired
    private CourseRepository courseRepository;

    @PostConstruct
    public void setup(){createInitialData();}


    public void createInitialData(){



        List<Student> students = this.studentRepository.findAll();

        if(students.size() > 0) {
            return;
        }

        List<Course> courses = this.courseRepository.findAll();

        if(courses.size() > 0) {
            return;
        }



        Student max = new Student();
        max.setUserName("MaxFirstUser");
        max.setFirstName("Max");
        max.setLastName("Musterman");
        max.seteMail("Max@mustermann.at");
        max = studentRepository.save(max);

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

        //COURSE
        Course swimming = new Course();
        swimming.setTitle("Swimming for Beginners");
        swimming.setDescription("If you want to learn how to swimm, this course is for you. We will start with the absolut basics");
        swimming.setMaxParticipants(3);
        swimming = courseRepository.save(swimming);

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


        //Bookings
        Booking bookingSwimmingMax = new Booking(swimming, max);
        bookingSwimmingMax = bookingRepository.save(bookingSwimmingMax);


        Booking bookingSwimmingLeah = new Booking(swimming, leah);
        bookingRepository.save(bookingSwimmingLeah);

        Booking bookingGymnasticsForLeah = new Booking(gymnastics, leah);
        bookingRepository.save(bookingGymnasticsForLeah);

    }
}
