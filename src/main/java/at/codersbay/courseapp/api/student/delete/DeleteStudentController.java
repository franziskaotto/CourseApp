package at.codersbay.courseapp.api.student.delete;

import at.codersbay.courseapp.api.ResponseBody;

import at.codersbay.courseapp.api.booking.Booking;
import at.codersbay.courseapp.api.booking.BookingRepository;
import at.codersbay.courseapp.api.student.Student;
import at.codersbay.courseapp.api.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;

@RestController
@RequestMapping("/api/students/")
public class DeleteStudentController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    BookingRepository bookingRepository;
    /**
     * Path: "localhost/api/students/{id}"
     * This method deletes a Student by Id from the DB.
     * @param id (long) Parameter containing the id of the to be deleted student.
     * @return message, if Delete was successful or not & HTTPStatus.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBody> deleteStudent(@PathVariable long id) {


        ResponseBody responseBody = new ResponseBody();

        try {
            Optional<Student> optionalStudent = this.studentRepository.findById(id);

            if(optionalStudent.isPresent()) {
                Student student = optionalStudent.get();

                System.out.println(student.getStudentBookings().size());
                for(Booking booking : new HashSet<>(student.getStudentBookings())) {
                    this.bookingRepository.delete(booking);
                }
            }

            this.studentRepository.flush();

            studentRepository.deleteById(id);

            optionalStudent = studentRepository.findById(id);

            if(optionalStudent.isPresent()){
                responseBody.addErrorMessage("delete of Student failed. ID:  " + id);
                return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
            } else {
                responseBody.addMessage("Delete successful");
                return new ResponseEntity<>(responseBody, HttpStatus.OK);
            }
        } catch (EmptyResultDataAccessException erde) {
            responseBody.addErrorMessage("No Student " + id + " available");
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);


        }

    }

    /**
     * Path: "localhost/api/students/userName/{userName}"
     * This Method deletes a Student by its UserName (String, unique) from the DB.
     * @param userName Parameter containing the UserName of the stundet to be deleted.
     * @return The ResponseBody contains a Message if delete was successful or not and a HTTPStatus.
     */
    @DeleteMapping("/userName/{userName}")
    public ResponseEntity<ResponseBody> deleteStudentByUserName(@PathVariable String userName) {
        Optional<Student> optionalStudent = this.studentRepository.findStudentByUserName(userName);

        ResponseBody responseBody = new ResponseBody();

        if(optionalStudent.isEmpty()) {
            responseBody.addErrorMessage("Could not find username : " + userName);
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        }

        Student student = optionalStudent.get();
        this.studentRepository.deleteById(student.getId());
        optionalStudent = studentRepository.findStudentByUserName(userName);

        if(optionalStudent.isPresent()){
            responseBody.addErrorMessage("Could not delete Student with userName: " + userName);
            return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
        } else {
            responseBody.addMessage("Delete successfull, " + userName + " has been deleted.");
            return new ResponseEntity<>(responseBody, HttpStatus.ACCEPTED);
        }
    }
}
