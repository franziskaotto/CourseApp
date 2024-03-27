package at.codersbay.courseapp.api;

import java.util.ArrayList;

public class ResponseBody {

    private ArrayList<String> message = new ArrayList<>(3);
    private ArrayList<String> errorMessage = new ArrayList<>(3);

    public boolean addMessage(String message) {return this.message.add(message);}

   public boolean addErrorMessage(String message) {return this.errorMessage.add(message);}


    //getter and setter
    public ArrayList<String> getMessage() {
        return message;
    }

    public void setMessage(ArrayList<String> message) {
        this.message = message;
    }

    public ArrayList<String> getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(ArrayList<String> errorMessage) {
        this.errorMessage = errorMessage;
    }
}
