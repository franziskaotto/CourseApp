package at.codersbay.courseapp.api.Student.delete;

import at.codersbay.courseapp.api.Student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stundents")
public class DeleteStudentController {

    @Autowired
    StudentRepository studentRepository;

    @DeleteMapping
}
