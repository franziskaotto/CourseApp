package at.codersbay.courseapp.api.booking.create;

public class CreateBookingDTO {

    private long courseId;

    private long studentId;




    public long getCourseId() { return courseId;}

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }
}
