package at.codersbay.courseapp.api.student;


import at.codersbay.courseapp.api.course.Course;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="Table_STUDENTS")
public class Student {

    @Id
    @GeneratedValue(generator = "students-sequence-generator")
    @GenericGenerator(
            name="students-sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "students_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )

    private long id;

    @Column (unique = true, nullable = false)
    private String userName;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String eMail;

    @JsonIgnore
    @ManyToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private Set<Course> courseList = new HashSet<>();

    //passwort nicht

    //beziehungen hier (many to many)
    //brauche noch eine Liste mit Courses!



    //Getters and setters
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }
}
