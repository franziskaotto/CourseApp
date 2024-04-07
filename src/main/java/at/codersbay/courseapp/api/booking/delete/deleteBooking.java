package at.codersbay.courseapp.api.booking.delete;

import at.codersbay.courseapp.api.ResponseBody;

import at.codersbay.courseapp.api.booking.Booking;
import at.codersbay.courseapp.api.booking.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/booking/")
public class deleteBooking {

    @Autowired
    BookingRepository bookingRepository;

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBody> deleteBookingById(@PathVariable long id){

        bookingRepository.deleteById(id);

        Optional<Booking> optionalBooking = bookingRepository.findById(id);

        System.out.println(optionalBooking);

        ResponseBody responseBody = new ResponseBody();

        if(optionalBooking.isPresent()) {
            responseBody.addErrorMessage("Could not delete Booking: " + id);
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        } else{
            responseBody.addMessage("Delete successfull");
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        }



    }
}
