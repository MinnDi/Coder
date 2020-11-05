package ru.croc.coder;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.croc.coder.domain.User;
import ru.croc.coder.repository.UserRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByEmail(){
        Optional<User> user = userRepository.findByEmailIgnoreCase("d_invalid@mail.com");
        assertThat(user).isNotEmpty();
    }

}
