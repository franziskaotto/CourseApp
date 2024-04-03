package at.codersbay.courseapp.api.booking;


import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class BookingID implements Serializable {
    @Column(name="course_ID")
    private long courseId;

    @Column(name="student_ID")
    private long studentId;

    protected BookingID() {};

    public BookingID(long courseId, long studentId) {
        this.courseId = courseId;
        this.studentId = studentId;
    }



}
