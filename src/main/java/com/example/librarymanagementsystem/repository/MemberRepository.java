package com.example.librarymanagementsystem.repository;

import com.example.librarymanagementsystem.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findAllByOrderByFirstNameAscMiddleNameAscLastNameAsc();

    Long countByType(String type);
}
