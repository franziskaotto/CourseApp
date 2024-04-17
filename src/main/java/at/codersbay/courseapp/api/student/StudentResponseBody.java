package at.codersbay.courseapp.api.student;

import at.codersbay.courseapp.api.ResponseBody;

public class StudentResponseBody extends ResponseBody {
    private Student student;

    public StudentResponseBody () {

    }


    public StudentResponseBody(Student student) {
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
