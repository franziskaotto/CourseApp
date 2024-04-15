package at.codersbay.courseapp.api.booking.create;


import at.codersbay.courseapp.api.booking.Booking;
import at.codersbay.courseapp.api.booking.BookingID;
import at.codersbay.courseapp.api.booking.BookingRepository;
import at.codersbay.courseapp.api.booking.BookingResponseBody;
import at.codersbay.courseapp.api.course.Course;
import at.codersbay.courseapp.api.course.CourseRepository;
import at.codersbay.courseapp.api.student.Student;
import at.codersbay.courseapp.api.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;



@RestController
@RequestMapping("/api/booking/")
public class CreateBookingController {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CreateBookingService createBookingService;


    /**
     * Path: "localhost/8081/api/booking/"
     * This Method creates (Post) a new Course Booking for a Student and adds it to the DB.
     * @param createBookingDTO DTO with a courseId(long) and a studentId (long).
     * @return BookingresponseBody contains the new Booking with a CourseId, the StudentId and a Message (String) & the HTTPStatus.
     */

    @PostMapping("/")
    public ResponseEntity<BookingResponseBody> createBooking(@RequestBody CreateBookingDTO createBookingDTO) {

        BookingResponseBody responseBody = new BookingResponseBody();

        if (createBookingDTO == null) {
            responseBody.addErrorMessage("PostBody CreateBooking is empty");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

        long courseIDToCheck = createBookingDTO.getCourseId();
        BookingID bookingID = new BookingID(createBookingDTO.getCourseId(), createBookingDTO.getStudentId());

        Optional<Course> optionalCourse = courseRepository.findById(courseIDToCheck);
        Optional<Booking> optionalBooking = bookingRepository.findById(bookingID);
        Optional<Student> optionalStudent = studentRepository.findById(createBookingDTO.getStudentId());

        if(optionalBooking.isPresent()) {
            responseBody.addErrorMessage("You already booked this course");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

        Booking booking = null;
        Course course = optionalCourse.get();
        Student student = optionalStudent.get();

        try {
            if(optionalCourse.isPresent()) {
                if(optionalCourse.get().getMaxParticipants() > bookingRepository
                        .findAllByCourseId(courseIDToCheck).size()) {
                    booking = this.createBookingService.createBooking(course, student);
                } else {
                    responseBody.addErrorMessage("Course is fully Booked. Please choose a different one");
                    return  new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
                }
            }
        } catch (CourseIsEmptyException | StudentIsEmptyException exception) {
            responseBody.addErrorMessage(exception.getMessage());
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new BookingResponseBody(booking), HttpStatus.OK);
    }

}
