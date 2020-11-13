package ru.croc.coder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.croc.coder.domain.*;
import ru.croc.coder.repository.ProblemRepository;
import ru.croc.coder.repository.UserRepository;

@Component
public class Init implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Init.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProblemRepository problemRepository;

    @Override
    public void run (String[] args) throws Exception {
        log.info("Init application");

        long numUser=userRepository.count();
        log.info("Number of users: {}",numUser);

        if (userRepository.findByEmailIgnoreCase("d_invalid@mail.com").isEmpty()){
            log.info("Creating initial user");
            Long userId = createUser("Dilyara", "Minnikhanova", "d_invalid@mail.com");
            log.info("Created user id: {}", userId);
             userId = createUser("Ivan", "Ivanov", "i_invalid@mail.com");
            log.info("Created user id: {}", userId);
        }

        User author = userRepository.findByEmailIgnoreCase("d_invalid@mail.com").get();
        Task task1 = createEasyJavaProblem(author, "Do a + b");
        log.info("Created problem id: {}", task1.getTaskId());
        Task task2 = createEasyJavaProblem(author, "Do a / b");
        log.info("Created problem id: {}", task2.getTaskId());
    }

    private Long createUser(String fName, String sName, String email) {
        User user=new User()
                .setFirstName(fName)
                .setLastName(sName)
                .setEmail(email)
                .setPassword("");

        Long userId = userRepository.save(user).getId();
        return userId;
    }

    private Task createEasyJavaProblem(User author, String description){
       Task task = new Task();
       task.setAuthor(author);
        task.setDescription(description);
        task.setTaskDifficulty(TaskDifficulty.EASY);
        task.setTemplate(new Code()
                        .setText("//Write your code here")
                        .setProgrammingLanguage(ProgrammingLanguage.JAVA));
        task.setMaxAttempts(3);
       return problemRepository.save(task);
    }
}
