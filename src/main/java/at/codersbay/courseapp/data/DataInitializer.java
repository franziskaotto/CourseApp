package at.codersbay.courseapp.data;

import at.codersbay.courseapp.api.Participants.Participants;
import at.codersbay.courseapp.api.Participants.ParticipantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class DataInitializer {
    @Autowired
    private ParticipantsRepository participantsRepository;

    //BookingRepository

    //CourseRepository

    @PostConstruct
    public void setup(){createInitialData();}


    public void createInitialData(){

        Participants max = new Participants();
        max.setUserName("MaxFirstUser");
        max.setFirstName("Max");
        max.setLastName("Musterman");
        max.seteMail("Max@mustermann.at");
        participantsRepository.save(max);

    }
}
