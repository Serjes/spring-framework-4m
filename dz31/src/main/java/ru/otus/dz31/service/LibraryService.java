package ru.otus.dz31.service;

import ru.otus.dz31.domain.Author;
import ru.otus.dz31.domain.Book;
import ru.otus.dz31.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface LibraryService {

    void addTemplateBook();

    void addBook(String title, String author, String authorLastname, String genre);

    void updateBook(Integer id, String title, String author, String authorLastName, String genre);

    List<Book> listBooks();

    List<Author> listAuthors();

    List<Genre> listGenres();

    long count();

    long countAuthors();

    long countGenres();

    Optional<Book> findBookById(Integer id);

    void delBook(Integer id);

    void printAuthorId(String name, String lastName);

}
