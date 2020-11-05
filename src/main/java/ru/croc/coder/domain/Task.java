package ru.croc.coder.domain;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Tasks")
public class Task {

    @ManyToOne
    private Course course;

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long taskId;

    @OneToOne
    private Solution solution;

}
