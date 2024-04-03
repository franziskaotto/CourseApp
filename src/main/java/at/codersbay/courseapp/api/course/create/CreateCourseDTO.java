package at.codersbay.courseapp.api.course.create;


import javax.persistence.Column;

public class CreateCourseDTO {
    private String title;

    private String description;

    private int maxParticipants;


    //getter and setter

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }
}
