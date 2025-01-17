package com.example.librarymanagementsystem.entity;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Getter
@Entity
@Table(name = "member")
public class Member implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "*Please select member type")
    @NotNull(message = "*Please select member type")
    @Column(name = "type")
    private String type;

    @NotEmpty(message = "*Please enter fisrt name")
    @NotNull(message = "*Please enter fisrt name")
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty(message = "*Please enter middle name")
    @NotNull(message = "*Please enter middle name")
    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "last_name")
    private String lastName;

    @NotEmpty(message = "*Please select gender")
    @NotNull(message = "*Please select gender")
    @Column(name = "gender")
    private String gender;

    @NotNull(message = "*Please enter birth date")
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "joining_date")
    private Date joiningDate;

    @Column(name = "contact")
    private String contact;

    @Column(name = "email")
    private String email;

    public Member(@NotNull String type, @NotNull String firstName, @NotNull String middleName, @NotNull String lastName,
                  @NotNull String gender, @NotNull Date dateOfBirth, @NotNull Date joiningDate) {
        super();
        this.type = type;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.joiningDate = joiningDate;
    }

    public Member() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
