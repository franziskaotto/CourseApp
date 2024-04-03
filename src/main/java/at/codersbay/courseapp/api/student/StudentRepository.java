package at.codersbay.courseapp.api.student;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    //create needed methods
    public Optional<Student> findStudentByUserName(String userName);

}
