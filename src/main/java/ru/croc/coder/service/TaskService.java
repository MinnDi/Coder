package ru.croc.coder.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.croc.coder.domain.*;
import ru.croc.coder.repository.SolutionRepository;
import ru.croc.coder.repository.TaskRepository;
import ru.croc.coder.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class TaskService {

    private static final Logger log = LoggerFactory.getLogger(TaskService.class);

    private  static final Random rnd = new Random(System.currentTimeMillis());

    private UserRepository userRepository;

    private TaskRepository taskRepository;

    private SolutionRepository solutionRepository;

    private UserContext userContext;

    public TaskService(UserRepository userRepository, TaskRepository problemRepository,
                          SolutionRepository solutionRepository, UserContext userContext) {
        this.userRepository = userRepository;
        this.taskRepository = problemRepository;
        this.solutionRepository = solutionRepository;
        this.userContext = userContext;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, noRollbackFor = TaskConstraintException.class)
    public Solution submit(Long problemId, String code) throws InterruptedException {

        User user = userContext.getCurrentUser();

        Task task = taskRepository.findById(problemId)
                .orElseThrow(NotFoundException::new);

        userRepository.save(user.setAttemptsCount(user.getAttemptsCount() + 1));

        if (task.getMaxAttempts() != null){
            long attempts = solutionRepository.countByAuthorAndTask(user, task);
             if (attempts >= task.getMaxAttempts())
                 throw new TaskConstraintException("Max attempts exceeded");
        }

        LocalDateTime solutionDate = LocalDateTime.now();

        if (solutionDate.isAfter(task.getDeadline()))
            throw  new TaskConstraintException("Deadline is missed");

        Solution solution = new Solution();
        solution.setAuthor(user);
        solution.setTask(task);
        solution.setTime(solutionDate);
        solution.setCode(new Code().setText(code).setProgrammingLanguage(task.getTemplate().getProgrammingLanguage()));
        solution.setPassed(null);
        solution.setSolutionStatus(SolutionStatus.QUEUED);

        return solutionRepository.save(solution);
    }

    @Async
    @Scheduled(fixedRate = 1000, initialDelay = 3000)
    public void checkSolution() throws InterruptedException {
        log.info("Scheduled check");

        Solution solution = null;
        try{
            Optional<Solution> result = peekNextSolution();
            if (result.isEmpty()){
                return;
            }
            solution = result.get();
            boolean passed = runTests(solution);
            solution.setPassed(passed);
        } finally {
            if (solution!=null) {
                solution.setSolutionStatus(SolutionStatus.DONE);
                solutionRepository.save(solution);
            }
        }
    }

    private boolean runTests(Solution solution) throws InterruptedException {
        Thread.sleep(15_000);
        return rnd.nextBoolean();
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, noRollbackFor = TaskConstraintException.class)
    public Optional<Solution> peekNextSolution(){
        // found QUEUED
        Optional<Solution> result = solutionRepository.findAnyQueued();
        if (result.isPresent()) {
            // set to IN PROGRESS
            Solution solution = result.get();
            solution.setSolutionStatus(SolutionStatus.IN_PROCESS);
            solutionRepository.save(solution);
        }
        return result;
    }
}
