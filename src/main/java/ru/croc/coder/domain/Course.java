package ru.croc.coder.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Course {

    @Id
    @Column(unique = true, nullable = false)
    private Long courseId;

    private Long teacherId;

    private String description;

}
