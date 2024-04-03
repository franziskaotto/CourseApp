package at.codersbay.courseapp.api.course.create;

public class MaxParticipantsIsEmptyException extends Exception{
    public MaxParticipantsIsEmptyException (String message) {
        super(message);
    }
}
