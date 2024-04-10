package at.codersbay.courseapp.api.booking;

import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, BookingID> {

    @Query("select b from Booking b where b.id.courseId =:courseId")
    List<Booking> findAllByCourseId(long courseId);

}
