package ru.croc.coder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.croc.coder.domain.Code;
import ru.croc.coder.domain.Solution;
import ru.croc.coder.domain.Task;
import ru.croc.coder.domain.User;
import ru.croc.coder.repository.ProblemRepository;
import ru.croc.coder.repository.SolutionRepository;
import ru.croc.coder.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class ProblemService {

    private  static final Random rnd = new Random(System.currentTimeMillis());

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProblemRepository problemRepository;

    @Autowired
    private SolutionRepository solutionRepository;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Solution submit(Long userId, Long problemId, String code){
        User user = userRepository.findById(userId)
                .orElseThrow(NotFoundException::new);
        Task task = problemRepository.findById(problemId)
                .orElseThrow(NotFoundException::new);

        if (task.getMaxAttempts() != null){
            long attempts = solutionRepository.countByAuthorAndTask(user, task);
             if (attempts >= task.getMaxAttempts())
                 throw new ProblemConstraintException("Max attempts exceeded");
        }

        Solution solution = new Solution();
        solution.setAuthor(user);
        solution.setTask(task);
        solution.setTime(LocalDateTime.now());
        solution.setCode(new Code().setText(code).setProgrammingLanguage(task.getTemplate().getProgrammingLanguage()));
        solution.setPassed(rnd.nextBoolean());
        return solutionRepository.save(solution);
    }

}
