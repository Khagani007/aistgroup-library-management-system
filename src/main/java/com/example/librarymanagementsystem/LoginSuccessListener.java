package com.example.librarymanagementsystem;

import com.example.librarymanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class LoginSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {
    private final UserService userService;
    private final HttpSession httpSession;

    @Autowired
    public LoginSuccessListener(UserService userService, HttpSession httpSession) {
        this.userService = userService;
        this.httpSession = httpSession;
    }

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        User user = (User) event.getAuthentication().getPrincipal();
        String displayName = userService.getByUsername(user.getUsername()).getDisplayName();
        httpSession.setAttribute("loggedInUserName", displayName);
    }

}
