package ru.otus.dz31.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@ToString
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Integer id;

    @Column(name="title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Comment> comments;

//    public Book() {
//    }

    public Book(String tittle, Author author, Genre genre) {
        this.title = tittle;
        this.author = author;
        this.genre = genre;
    }

//    public Author getAuthor() {
//        return author;
//    }
//
//    public Genre getGenre() {
//        return genre;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public void setAuthor(Author author) {
//        this.author = author;
//    }
//
//    public void setGenre(Genre genre) {
//        this.genre = genre;
//    }
//
//    @Override
//    public String toString() {
//        return "Book{" +
//                "id=" + id +
//                ", tittle='" + title + '\'' +
//                '}';
//    }
}
