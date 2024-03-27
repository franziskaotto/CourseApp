package at.codersbay.courseapp.api.Student.create;

public class EmailIsEmptyException extends Exception{
    EmailIsEmptyException(String message) {
        super(message);
    }
}
