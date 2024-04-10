package at.codersbay.courseapp.api.student.create;

//info: alles was DTO überprüfung ist, passiert im controller, alle anderen in der service class

import at.codersbay.courseapp.api.student.Student;
import at.codersbay.courseapp.api.student.StudentResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/students")
public class CreateStudentController {
@Autowired
private CreateStudentService createStudentService;

    @PostMapping
    public ResponseEntity<StudentResponseBody> createNewStudent(@RequestBody CreateStudentDTO createStudentDTO) {

        if(createStudentDTO == null) {
            StudentResponseBody response = new StudentResponseBody();
            response.addErrorMessage("PostBody is empty");

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        Student student = null;
        StudentResponseBody response = new StudentResponseBody();
        try {
            if(testIfEmailIsValid(createStudentDTO.geteMail())){
                student = this.createStudentService.createNewStudent(createStudentDTO.getUserName(), createStudentDTO.getFirstName(), createStudentDTO.getLastName(), createStudentDTO.geteMail());

            } else {
                response.addErrorMessage("your Email is missing an @");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }catch (UserNameIsEmptyException | FirstNameIsEmptyException | LastNameIsEmptyException | EmailIsEmptyException exception) {

            response.addErrorMessage(exception.getMessage());

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new StudentResponseBody(student), HttpStatus.OK);

    }




    public boolean testIfEmailIsValid(String email){
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();

    }

}
