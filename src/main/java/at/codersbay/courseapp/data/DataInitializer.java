package at.codersbay.courseapp.data;

import at.codersbay.courseapp.api.Student.Student;
import at.codersbay.courseapp.api.Student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class DataInitializer {
    @Autowired
    private StudentRepository studentRepository;

    //BookingRepository

    //CourseRepository

    @PostConstruct
    public void setup(){createInitialData();}


    public void createInitialData(){

        Student max = new Student();
        max.setUserName("MaxFirstUser");
        max.setFirstName("Max");
        max.setLastName("Musterman");
        max.seteMail("Max@mustermann.at");
        studentRepository.save(max);

    }
}
