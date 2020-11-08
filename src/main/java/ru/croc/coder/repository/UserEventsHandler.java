package ru.croc.coder.repository;

import org.springframework.data.rest.core.annotation.*;
import org.springframework.stereotype.Component;
import ru.croc.coder.domain.User;

@Component
@RepositoryEventHandler(User.class)
public class UserEventsHandler {

    @HandleBeforeCreate
    @HandleBeforeSave
    public void handleUserBeforeSave(User user){
        user.setPassword("hash"+user.getPassword().hashCode());
        System.out.println("!!!: " + user);
    }

    @HandleAfterSave
    @HandleAfterCreate
    public void handleUserAfterSave(User user) {
        System.out.println("!!!: " + user);
    }
}
