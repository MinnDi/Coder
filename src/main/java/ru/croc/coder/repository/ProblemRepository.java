package ru.croc.coder.repository;

import org.springframework.data.repository.CrudRepository;
import ru.croc.coder.domain.Task;

public interface ProblemRepository extends CrudRepository <Task, Long> {
}
