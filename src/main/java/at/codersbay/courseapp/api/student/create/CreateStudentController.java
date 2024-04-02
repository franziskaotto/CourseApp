package at.codersbay.courseapp.api.student.create;

//info: alles was DTO überprüfung ist, passiert im controller, alle anderen in der service class

import at.codersbay.courseapp.api.student.Student;
import at.codersbay.courseapp.api.student.StudentResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class CreateStudentController {
@Autowired
private CreateStudentService createStudentService;

//irgendwo hier noch abfragen, ob es den Student schon gibt => weil die eine Coloumn auf Unique ist
    @PostMapping
    public ResponseEntity<StudentResponseBody> createNewStudent(@RequestBody CreateStudentDTO createStudentDTO) {

        if(createStudentDTO == null) {
            StudentResponseBody response = new StudentResponseBody();
            response.addErrorMessage("PostBody is empty");

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Student student = null;

        try {
            student = this.createStudentService.createNewStudent(createStudentDTO.getUserName(), createStudentDTO.getFirstName(), createStudentDTO.getLastName(), createStudentDTO.geteMail());
        }catch (UserNameIsEmptyException | FirstNameIsEmptyException | LastNameIsEmptyException | EmailIsEmptyException exception) {
            StudentResponseBody response = new StudentResponseBody();
            response.addErrorMessage(exception.getMessage());

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new StudentResponseBody(student), HttpStatus.OK);

    }


}
