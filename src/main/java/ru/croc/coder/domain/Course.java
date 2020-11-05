package ru.croc.coder.domain;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Courses")
public class Course {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long courseId;

    @ManyToOne
    private Teacher teacher;

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() { return courseId; }
}
