package at.codersbay.courseapp.api.booking;

import at.codersbay.courseapp.api.course.Course;
import at.codersbay.courseapp.api.student.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.awt.print.Book;

@Entity
@Table(name = "TB_BOOKING")
public class Booking {

    @EmbeddedId
    private BookingID id;



    @JsonIgnore
    @ManyToOne //das erste bezieht sich auf die classe in der grad bin, in dem fall Booking
    @MapsId("courseId")
    private Course course;


    @JsonIgnore
    @ManyToOne
    @MapsId("studentId")
    private Student student;

    public Booking () {

    }

    public Booking(Course course, Student student) {
        this.course = course;
        this.student = student;
        this.id = new BookingID(course.getId(), student.getId());
    }


    public BookingID getId() {
        return id;
    }

    public void setId(BookingID id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public boolean equals(Object other) {
        if(this == other) {
            return true;
        }

        if(other == null || !(other instanceof Booking)) {
            return false;
        }

        Booking otherBooking = (Booking) other;

        if(this.id == null && otherBooking.getId() == null) {
            return true;
        }

        return this.id.equals(otherBooking.getId());
    }

    @Override
    public int hashCode() {
        if(this.id == null) {
            return 0;
        }

        return this.id.hashCode();
    }
}
