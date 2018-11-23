package ru.otus.dz31.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@ToString
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="firstName")
    private String firstName;
    @Column(name="lastName")
    private String lastName;

    @OneToMany(mappedBy="author", fetch=FetchType.LAZY, orphanRemoval = true)
    private Set<Book> books;

//    public Author() {
//    }

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

//    public int getId() {
//        return id;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
}
