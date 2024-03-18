package at.codersbay.courseapp.api.Course;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Table(name="TB_COURSE")
public class Course {

        @Id
        @GeneratedValue(generator = "course-sequence-generator")
        @GenericGenerator(
                name="course-sequence-generator",
                strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
                parameters = {
                        @org.hibernate.annotations.Parameter(name = "sequence_name", value = "user_sequence"),
                        @org.hibernate.annotations.Parameter(name = "initial_value", value = "1"),
                        @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
                }
        )

        private long id;

        @Column()
        private String title;

        @Column()
        private String description;

        @Column()
        private int maxParticipants;

        //@ManyToMany()
//        @JoinTable(
//                name="TB_COURSES_USERS"
//        )
//










}
