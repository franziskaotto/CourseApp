package at.codersbay.courseapp.api.student.create;

public class LastNameIsEmptyException extends Exception{

    LastNameIsEmptyException (String message) {
        super(message);
    }
}
