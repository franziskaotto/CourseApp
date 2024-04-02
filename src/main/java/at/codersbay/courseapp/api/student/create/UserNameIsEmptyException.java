package at.codersbay.courseapp.api.student.create;

public class UserNameIsEmptyException extends Exception{
    UserNameIsEmptyException(String message) {
        super(message);
    };

}
