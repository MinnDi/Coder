package ru.croc.coder.domain;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Solution")
public class Solution {

    @ManyToOne
    private Course course;

    @OneToOne
    private Task task;

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long solutionId;

}
