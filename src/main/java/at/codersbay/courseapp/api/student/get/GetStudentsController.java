package at.codersbay.courseapp.api.student.get;


import at.codersbay.courseapp.api.ResponseBody;
import at.codersbay.courseapp.api.student.Student;
import at.codersbay.courseapp.api.student.StudentRepository;
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
@RequestMapping("/api/students")
public class GetStudentsController {

    @Autowired
    private StudentRepository studentRepository;

    /**
     * Path: "localhost/8081/api/students/".
     * This Method lists all Students which are currently in the DB.
     * @return returns a List with all Students.
     */
    @GetMapping("/")
    public ResponseEntity<List<Student>> getAll() {
        List<Student>  students = studentRepository.findAll();

       if(students.size() == 0) {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
        return ResponseEntity.ok(students);
    }

    /**
     * Path: "localhost/8081/api/students/{id}"
     * This method finds one Student by Id (long) in the DB.
     * @param id (long) The Pathvariable is the Id of the student to be found in the DB
     * @return This method returns the student with the Id from the Pathvariable and a HTTPStatus
     */
    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable long id) {


        Optional<Student> optionalStudent = studentRepository.findById(id);


        if (!optionalStudent.isPresent()) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Student student = optionalStudent.get();

        return ResponseEntity.ok(student);
    }
}
