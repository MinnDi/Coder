package ru.croc.coder.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Task {

    private Long coureId;

    @Id
    private Long taskId;

    private Long solutionId;

}
