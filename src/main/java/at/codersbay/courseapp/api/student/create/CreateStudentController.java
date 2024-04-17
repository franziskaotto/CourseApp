package at.codersbay.courseapp.api.student.create;

//info für mich zum lernen: alles was DTO überprüfung ist, passiert im controller, alle anderen in der service class

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


    /**
     * Path: "localhost/api/students/"
     * This Method (Post) creates a Student.If one Key-Value-Pair is left empty, one of the listed down below exceptions is thrown
     * @param createStudentDTO The DTO contains userName (String), firstName (String), LastName(String) and email(String) of the new student.
     * @return contains userName (String), firstName (String), LastName(String) and email(String) of the new student

     * @throws EmailIsEmptyException extends Exception. If the email of the student is left empty, the EmailIsEmptyException is thrown
     * @throws FirstNameIsEmptyException extends Exception. If the first name of the student is left empty, the FirstNameException is thrown
     * @throws LastNameIsEmptyException extends Exception. If the last name of the student is left empty, the LastNameIsEmptyException is thrown
     * @throws UserNameIsEmptyException extends Exception. If the username of the student is left empty, the UserNameIsEmptyException is thrown
     *
     */


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
