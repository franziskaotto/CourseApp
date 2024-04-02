package at.codersbay.courseapp.api.student.update;


import at.codersbay.courseapp.api.student.Student;
import at.codersbay.courseapp.api.student.StudentRepository;
import at.codersbay.courseapp.api.student.StudentResponseBody;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/students/")
public class UpdateStudentController {

    //noch mal vielleicht umschreiben Oder ergänzen: Suche mit id als PathVariable
    @Autowired
    StudentRepository studentRepository;

    @PatchMapping
    public ResponseEntity<StudentResponseBody> updateStudent(@RequestBody UpdateStudentDTO updateStudentDTO) {
        if(updateStudentDTO == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }
        Optional<Student> optionalStudent = this.studentRepository.findById(updateStudentDTO.getId());

        if(optionalStudent.isEmpty()) {
            StudentResponseBody studentResponseBody = new StudentResponseBody();
            studentResponseBody.addErrorMessage("Could not find Student by ID: " + updateStudentDTO.getId());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Student student = optionalStudent.get();


        /**StringUtils Klasse ersetzt dieses Monster:
         if(updateBookDTO.getTitle() != null && updateBookDTO.getTitle().length() > 0) {
         }
         */

        //abfrage, welches der Key-value pairs NICHT leer ist
        if(!StringUtils.isEmpty(updateStudentDTO.getUserName())) {
            student.setUserName(updateStudentDTO.getUserName());
        }

        if(!StringUtils.isEmpty(updateStudentDTO.getFirstName())) {
            student.setUserName(updateStudentDTO.getFirstName());
        }

        if(!StringUtils.isEmpty(updateStudentDTO.getLastName())) {
            student.setLastName(updateStudentDTO.getLastName());
        }

        if(!StringUtils.isEmpty(updateStudentDTO.geteMail())){
            student.seteMail(updateStudentDTO.geteMail());
        }

        this.studentRepository.save(student);

        StudentResponseBody responseBody = new StudentResponseBody();
        responseBody.setStudent(student);

        return ResponseEntity.ok(responseBody);
    }
}