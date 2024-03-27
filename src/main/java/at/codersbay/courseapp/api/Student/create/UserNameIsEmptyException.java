package at.codersbay.courseapp.api.Student.create;

public class UserNameIsEmptyException extends Exception{
    UserNameIsEmptyException(String message) {
        super(message);
    };

}
