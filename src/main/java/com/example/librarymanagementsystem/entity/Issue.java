package com.example.librarymanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Entity
@Table(name = "issue")
public class Issue implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "issue_date")
    private Date issueDate;

    @Column(name = "notes")
    private String notes;

    @Column(name = "expected_return_date")
    private Date expectedReturnDate;

    @Column(name = "returned")
    private Integer returned;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @JsonIgnore
    @OneToMany(mappedBy = "issue", cascade = CascadeType.ALL)
    private List<IssuedBook> issuedBooks;

    public void setId(Long id) {
        this.id = id;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setExpectedReturnDate(Date expectedReturnDate) {
        this.expectedReturnDate = expectedReturnDate;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void setIssuedBooks(List<IssuedBook> issuedBooks) {
        this.issuedBooks = issuedBooks;
    }

    public void setReturned(Integer returned) {
        this.returned = returned;
    }

}
