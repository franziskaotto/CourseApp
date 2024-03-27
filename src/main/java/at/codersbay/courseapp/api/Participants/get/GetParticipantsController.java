package at.codersbay.courseapp.api.Participants.get;


import at.codersbay.courseapp.api.Participants.Participants;
import at.codersbay.courseapp.api.Participants.ParticipantsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/participants")
public class GetParticipantsController {

    @Autowired
    private ParticipantsRepository participantsRepository;

    @GetMapping("/")
    public ResponseEntity<List<Participants>> getAll() {
        List<Participants>  participants = participantsRepository.findAll();
        return ResponseEntity.ok(participants);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Participants> getById(@PathVariable long id) {


        Optional<Participants> optionalParticipant = participantsRepository.findById(id);


        if (!optionalParticipant.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        Participants participant = optionalParticipant.get();

        return ResponseEntity.ok(participant);
    }
}
