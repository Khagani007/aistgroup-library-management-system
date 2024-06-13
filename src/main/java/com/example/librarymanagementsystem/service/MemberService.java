package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.util.AppConstants;
import com.example.librarymanagementsystem.entity.Member;
import com.example.librarymanagementsystem.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final IssueService issueService;

    @Autowired
    public MemberService(MemberRepository memberRepository, IssueService issueService) {
        this.memberRepository = memberRepository;
        this.issueService = issueService;
    }

    public Long getTotalCount() {
        return memberRepository.count();
    }

    public Long getParentsCount() {
        return memberRepository.countByType(AppConstants.MEMBER_PARENT);
    }

    public Long getStudentsCount() {
        return memberRepository.countByType(AppConstants.MEMBER_STUDENT);
    }

    public List<Member> getAll() {
        return memberRepository.findAllByOrderByFirstNameAscMiddleNameAscLastNameAsc();
    }

    public Member get(Long id) {
        return memberRepository.findById(id).get();
    }

    public Member addNew(Member member) {
        member.setJoiningDate(new Date());
        return memberRepository.save(member);
    }

    public Member save(Member member) {
        return memberRepository.save(member);
    }

    public void delete(Member member) {
        memberRepository.delete(member);
    }

    public void delete(Long id) {
        memberRepository.deleteById(id);
    }

    public boolean hasUsage(Member member) {
        return issueService.getCountByMember(member) > 0;
    }

}
