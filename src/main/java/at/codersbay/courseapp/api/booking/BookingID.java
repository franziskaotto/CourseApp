package at.codersbay.courseapp.api.booking;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class BookingID extends Object implements Serializable {


    @Column(name="course_ID")
    private long courseId;

    @Column(name="student_ID")
    private long studentId;


    @Column(name="BookingDate")
    private LocalDate bookingDate;




    protected BookingID() {};

    public BookingID(long courseId, long studentId) {
        this.courseId = courseId;
        this.studentId = studentId;
        this.bookingDate = LocalDate.now();
    }







    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if(o == null ||  getClass() != o.getClass()) {
            return false;
        }

        BookingID that = (BookingID) o;
        return Objects.equals(courseId, that.courseId) && Objects.equals(studentId, that.studentId);


    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId, studentId);
    }

    //getter and setter


    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }
}
