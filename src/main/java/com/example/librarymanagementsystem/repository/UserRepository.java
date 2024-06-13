package com.example.librarymanagementsystem.repository;

import com.example.librarymanagementsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAllByOrderByDisplayNameAsc();

    List<User> findAllByActiveOrderByDisplayNameAsc(Integer active);

    User findByUsername(String username);
}
