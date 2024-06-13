package com.example.librarymanagementsystem;

import com.example.librarymanagementsystem.util.AppConstants;
import com.example.librarymanagementsystem.entity.User;
import com.example.librarymanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {
    private final UserService userService;

    @Autowired
    public ApplicationStartup(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        initDatabaseEntities();
    }


    private void initDatabaseEntities() {

        if (userService.getAllUsers().isEmpty()) {
            userService.addNew(new User("Admin", "admin", "admin", AppConstants.ROLE_ADMIN));
            userService.addNew(new User("Librarian", "librarian", "librarian", AppConstants.ROLE_LIBRARIAN));
        }

    }
}