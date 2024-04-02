package at.codersbay.courseapp.api.Student.delete;

import at.codersbay.courseapp.api.ResponseBody;

import at.codersbay.courseapp.api.Student.Student;
import at.codersbay.courseapp.api.Student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/students/")
public class DeleteStudentController {

    @Autowired
    StudentRepository studentRepository;

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBody> deleteStudent(@PathVariable long id) {

        studentRepository.deleteById(id);

        Optional<Student> optionalStudent =studentRepository.findById(id);

        ResponseBody responseBody = new ResponseBody();


        if(optionalStudent.isPresent()) {
            responseBody.addErrorMessage("delete of Student failed. ID:  " + id);
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        } else if (optionalStudent.isEmpty()) {
            responseBody.addMessage("User successfully deleted");
            return new ResponseEntity<>(responseBody, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
