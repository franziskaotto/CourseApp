package at.codersbay.courseapp.api.booking.create;

import at.codersbay.courseapp.api.booking.Booking;
import at.codersbay.courseapp.api.booking.BookingRepository;
import at.codersbay.courseapp.api.course.Course;
import at.codersbay.courseapp.api.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateBookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public Booking createBooking(Course course, Student student) throws CourseIsEmptyException, StudentIsEmptyException{

        if(course == null){
            throw  new CourseIsEmptyException("Course is empty or null");
        } else if(student == null){
            throw new StudentIsEmptyException("Student is emptyExeption");
        }


        Booking booking = new Booking(course, student);

        return bookingRepository.save(booking);

    }
}
