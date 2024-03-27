package at.codersbay.courseapp.api.Student.create;

public class LastNameIsEmptyException extends Exception{

    LastNameIsEmptyException (String message) {
        super(message);
    }
}
