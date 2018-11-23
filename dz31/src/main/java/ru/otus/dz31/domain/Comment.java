package ru.otus.dz31.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@ToString
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

//    public Comment() {
//    }

    public Comment(String content, Book book) {
        this.content = content;
        this.book = book;
    }

//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public Book getBook() {
//        return book;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    @Override
//    public String toString() {
//        return "Comment{" +
//                "content='" + content + '\'' +
//                ", book=" + book +
//                '}';
//    }
}
