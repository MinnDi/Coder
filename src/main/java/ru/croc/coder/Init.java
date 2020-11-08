package ru.croc.coder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.croc.coder.domain.User;
import ru.croc.coder.repository.UserRepository;

@Component
public class Init implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Init.class);

    @Autowired
    private UserRepository userRepository;

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
}
