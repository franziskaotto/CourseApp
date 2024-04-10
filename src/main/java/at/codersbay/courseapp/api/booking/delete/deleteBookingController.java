package at.codersbay.courseapp.api.booking.delete;

import at.codersbay.courseapp.api.ResponseBody;

import at.codersbay.courseapp.api.booking.Booking;
import at.codersbay.courseapp.api.booking.BookingID;
import at.codersbay.courseapp.api.booking.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/booking/")
public class deleteBookingController {

    @Autowired
    BookingRepository bookingRepository;


    /**
     * Path: "localhost/8081/api/booking/delete/{courseId}/{studentId}"
     * This method deletes a CourseBooking from the DB. It also removes the booking from the List of bookings in Course and Student
     * @param courseId Parameter containing the courseId (long) of the booked Course to be deleted.
     * @param studentId Parameter containing the studentId (long) of the booked student in the Course to be deleted.
     * @return Status message, if booking was successfully deleted or not.
     */
    @DeleteMapping("/delete/{courseId}/{studentId}")
    public ResponseEntity<ResponseBody> deleteBookingById
            (@PathVariable long courseId, @PathVariable long studentId)

    {

        //Kann auch vorher testen ob die Buchung da ist oder nicht

        BookingID bookingID = new BookingID(courseId, studentId);
        ResponseBody responseBody = new ResponseBody();
        try {
            bookingRepository.deleteById(bookingID);


            Optional<Booking> optionalBooking = bookingRepository.findById(bookingID);

            if(optionalBooking.isPresent()) {
                responseBody.addErrorMessage("Could not delete Booking: " + courseId);
                return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);

            } else {
                responseBody.addMessage("Delete successful");
                return new ResponseEntity<>(responseBody, HttpStatus.OK);
            }
        } catch (EmptyResultDataAccessException erde) {
            responseBody.addErrorMessage("No Bookings available");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);

        }
    }
}
