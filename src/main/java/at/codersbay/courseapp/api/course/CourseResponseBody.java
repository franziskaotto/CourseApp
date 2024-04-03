package at.codersbay.courseapp.api.course;

import org.apache.catalina.connector.Response;

public class CourseResponseBody extends Response {
    private Course course;

    public CourseResponseBody() {

    }
    private CourseResponseBody(Course course) {this.course = course;}


    //getter and setter

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
