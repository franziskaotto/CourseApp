package at.codersbay.courseapp.api.student.get;


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

    @GetMapping("/")
    public ResponseEntity<List<Student>> getAll() {
        List<Student>  students = studentRepository.findAll();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getById(@PathVariable long id) {


        Optional<Student> optionalStudent = studentRepository.findById(id);


        if (!optionalStudent.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        Student student = optionalStudent.get();

        return ResponseEntity.ok(student);
    }

    //enter more getBy...() here
}
