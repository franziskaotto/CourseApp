package at.codersbay.courseapp.api.student.create;


import at.codersbay.courseapp.api.student.Student;
import at.codersbay.courseapp.api.student.StudentRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateStudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Student createNewStudent(String userName, String firstName, String lastName, String eMail)
            throws UserNameIsEmptyException, FirstNameIsEmptyException, LastNameIsEmptyException, EmailIsEmptyException {
        if(StringUtils.isEmpty(userName)) {
            throw new UserNameIsEmptyException("Username is empty");
        } else if (StringUtils.isEmpty(firstName)) {
            throw new FirstNameIsEmptyException("FirstName is empty");
        } else if (StringUtils.isEmpty(lastName)) {
            throw new LastNameIsEmptyException("LastName is empty");
        } else if (StringUtils.isEmpty(eMail)) {
            throw new EmailIsEmptyException("Email is empty");
        }

        Student student = new Student();
        student.setUserName(userName);
        student.setFirstName(firstName);
        student.setLastName(lastName );
        student.seteMail(eMail);

        return this.studentRepository.save(student);
    }
}
