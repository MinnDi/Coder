package ru.croc.coder.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Solution {

    private Long courseId;

    private Long taskId;

    @Id
    private Long solutionId;

}
