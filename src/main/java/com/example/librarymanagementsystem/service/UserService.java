package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.entity.User;
import com.example.librarymanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers() {
        return userRepository.findAllByOrderByDisplayNameAsc();
    }


    public List<User> getAllActiveUsers() {
        return
                userRepository.findAllByActiveOrderByDisplayNameAsc(1);
    }

    public User getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User getById(Long id) {
        return userRepository.findById(id).get();
    }

    public User addNew(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreatedDate(new Date());
        user.setLastModifiedDate(user.getCreatedDate());
        user.setActive(1);
        return userRepository.save(user);
    }

    public User update(User user) {
        user.setLastModifiedDate(new Date());
        return userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
