package at.codersbay.courseapp.api.booking.get;


import at.codersbay.courseapp.api.booking.Booking;
import at.codersbay.courseapp.api.booking.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/booking/")
public class GetBookingController {

    @Autowired
    private BookingRepository bookingRepository;


    /**
     * Path: "localhost/8081/api/booking/"
     * This method lists all active bookings which are currently in the DB.
     * @return List with all bookings.
     */
    @GetMapping("/")
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return ResponseEntity.ok(bookings);
    }

}
