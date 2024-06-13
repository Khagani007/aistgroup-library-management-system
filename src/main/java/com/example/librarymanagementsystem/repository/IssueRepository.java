package com.example.librarymanagementsystem.repository;

import com.example.librarymanagementsystem.entity.Issue;
import com.example.librarymanagementsystem.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {
    List<Issue> findByReturned(Integer returned);

    Long countByMemberAndReturned(Member member, Integer returned);
}
