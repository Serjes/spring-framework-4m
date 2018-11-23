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
@Table(name = "genres")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @OneToMany(mappedBy="genre", fetch=FetchType.LAZY, orphanRemoval = true)
    private Set<Book> books;

//    public Genre() {
//    }

    public Genre(String name) {
        this.name = name;
    }
//
//    public String getName() {
//        return name;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
}
