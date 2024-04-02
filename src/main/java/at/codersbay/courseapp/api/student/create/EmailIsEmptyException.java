package at.codersbay.courseapp.api.student.create;

public class EmailIsEmptyException extends Exception{
    EmailIsEmptyException(String message) {
        super(message);
    }
}
