package at.codersbay.courseapp.api.course.create;

public class TitleIsEmptyException extends Exception {
    public TitleIsEmptyException (String message) {
        super(message);
    }

}
