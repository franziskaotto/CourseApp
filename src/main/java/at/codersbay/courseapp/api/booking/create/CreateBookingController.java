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




    @PostMapping("/")
    public ResponseEntity<BookingResponseBody> createBooking(@RequestBody CreateBookingDTO createBookingDTO) {

        BookingResponseBody responseBody = new BookingResponseBody();

        if (createBookingDTO == null) {
            responseBody.addErrorMessage("PostBody CreateBooking is empty");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

        BookingID bookingID = new BookingID(createBookingDTO.getCourseId(), createBookingDTO.getStudentId());

        Optional<Booking> optionalBooking = bookingRepository.findById(bookingID);
        Optional<Course> optionalCourse = courseRepository.findById(createBookingDTO.getCourseId());
        Optional<Student> optionalStudent = studentRepository.findById(createBookingDTO.getStudentId());

        if(optionalBooking.isPresent()) {
            responseBody.addErrorMessage("You already booked this course");
            return new ResponseEntity<>(responseBody, HttpStatus.CONFLICT);
        }

        Booking booking = null;
        Course course = optionalCourse.get();
        Student student = optionalStudent.get();

        try {
            booking = this.createBookingService.createBooking(course, student);

        } catch (CourseIsEmptyException | StudentIsEmptyException exception) {
            responseBody.addErrorMessage(exception.getMessage());
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new BookingResponseBody(booking), HttpStatus.OK);





        // Student student = this.studentRepository.findById(createBookingDTO.getStudentID())

    }

}
