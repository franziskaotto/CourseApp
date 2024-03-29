package at.codersbay.courseapp.api.Student;

import at.codersbay.courseapp.api.ResponseBody;

public class StudentResponseBody extends ResponseBody {
    private Student student;

    public StudentResponseBody () {

    }


    //creating 2. constructor with passing value
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
