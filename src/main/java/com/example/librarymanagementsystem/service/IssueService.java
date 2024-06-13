package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.util.AppConstants;
import com.example.librarymanagementsystem.entity.Issue;
import com.example.librarymanagementsystem.entity.Member;
import com.example.librarymanagementsystem.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class IssueService {
    private final IssueRepository issueRepository;

    @Autowired
    public IssueService(IssueRepository issueRepository) {
        this.issueRepository = issueRepository;
    }

    public List<Issue> getAll() {
        return issueRepository.findAll();
    }

    public Issue get(Long id) {
        return issueRepository.findById(id).get();
    }

    public List<Issue> getAllUnreturned() {
        return issueRepository.findByReturned(AppConstants.BOOK_NOT_RETURNED);
    }

    public Issue addNew(Issue issue) {
        issue.setIssueDate(new Date());
        issue.setReturned(AppConstants.BOOK_NOT_RETURNED);
        return issueRepository.save(issue);
    }

    public Issue save(Issue issue) {
        return issueRepository.save(issue);
    }

    public Long getCountByMember(Member member) {
        return issueRepository.countByMemberAndReturned(member, AppConstants.BOOK_NOT_RETURNED);
    }
}
