package ru.croc.coder.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.croc.coder.domain.Solution;
import ru.croc.coder.domain.Task;
import ru.croc.coder.domain.User;

public interface SolutionRepository extends CrudRepository<Solution, Long> {

    long countByAuthorAndTask(User author, Task task);

    @Override
    @RestResource(exported = false)
    <S extends  Solution> S save (S entity);

}
