package com.example.librarymanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Getter
@Entity
@Table(name = "book")
public class Book implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "*Please enter book title")
    @NotBlank(message = "*Please enter book title")
    @Column(name = "title")
    private String title;

    @NotNull(message = "*Please enter book tag")
    @NotBlank(message = "*Please enter book tag")
    @Column(name = "tag")
    private String tag;

    @NotNull(message = "*Please enter book authors")
    @NotBlank(message = "*Please enter book authors")
    @Column(name = "authors")
    private String authors;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "status")
    private Integer status;

    @Column(name = "create_date")
    private Date createDate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id")
    @NotNull(message = "*Please select category")
    private Category category;

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


}
