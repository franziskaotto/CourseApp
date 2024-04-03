package at.codersbay.courseapp.api.course;

import at.codersbay.courseapp.api.ResponseBody;

public class CourseResponseBody extends ResponseBody {
    private Course course;

    public CourseResponseBody() {

    }


    //creating 2. constructor with passing value
    public CourseResponseBody(Course course) {this.course = course;}


    //getter and setter

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
