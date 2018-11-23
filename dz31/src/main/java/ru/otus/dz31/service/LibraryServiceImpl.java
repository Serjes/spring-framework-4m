package ru.otus.dz31.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dz31.domain.Author;
import ru.otus.dz31.domain.Book;
import ru.otus.dz31.domain.Genre;
import ru.otus.dz31.repository.AuthorRepository;
import ru.otus.dz31.repository.BookRepository;
import ru.otus.dz31.repository.GenreRepository;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class LibraryServiceImpl implements LibraryService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public void addTemplateBook() {
        addBook("Азазель", "Борис","Акунин", "детектив");
    }

    @Override
    @HystrixCommand(commandKey = "addBook", groupKey = "LibraryService")
    public void addBook(String title, String authorName, String authorLastName, String genreName) {
        Optional<Author> authorOptional = authorRepository.findByFirstNameAndLastName(authorName, authorLastName);
        Author author;
        if (!authorOptional.isPresent()) {
            author = new Author(authorName, authorLastName);
            authorRepository.save(author);
        } else {
            author = authorOptional.get();
        }
        Genre genre = genreRepository.findByName(genreName);
        if (genre == null) {
            genre = new Genre(genreName);
            genreRepository.save(genre);
        }
        Book book = new Book(title, author, genre);
        bookRepository.save(book);

    }

    @Override
    @HystrixCommand(commandKey = "updateBook", groupKey = "LibraryService")
    public void updateBook(Integer id, String title, String authorName, String authorLastName, String genreName) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isPresent()) {
            Author author = null;
            Optional<Author> authorOptional = authorRepository.findByFirstNameAndLastName(authorName, authorLastName);
            if (!authorOptional.isPresent()){
                author = new Author(authorName, authorLastName);
                authorRepository.save(author);
            } else {
                author = authorOptional.get();
            }
            Genre genre = null;
            Optional<Genre> genreOptional = Optional.ofNullable(genreRepository.findByName(genreName));
            if (!genreOptional.isPresent()){
                genre = new Genre(genreName);
                genreRepository.save(genre);
            } else {
                genre = genreOptional.get();
            }
            Book book = bookOptional.get();
            book.setTitle(title);
            book.setAuthor(author);
            book.setGenre(genre);
            bookRepository.save(book);
        }

    }

    @Override
    @Transactional(readOnly = true)
    @HystrixCommand(commandKey = "listBook", groupKey = "LibraryService",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
            }
    )
    public List<Book> listBooks() {
        return bookRepository.findAll();

    }

    @Override
    @Transactional(readOnly = true)
    @HystrixCommand(commandKey = "count", groupKey = "LibraryService")
    public long count() {
        return bookRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public long countAuthors() {
        return authorRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public long countGenres() {
        return genreRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    @HystrixCommand(commandKey = "findBook", groupKey = "LibraryService")
    public Optional<Book> findBookById(Integer id) {
        return bookRepository.findById(id);
    }

    @Override
    @HystrixCommand(commandKey = "delBook", groupKey = "LibraryService",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
            })
    public void delBook(Integer id) {
        bookRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public void printAuthorId(String name, String lastName) {
        Optional<Author> authorOptional = authorRepository.findByFirstNameAndLastName(name, lastName);
        String authorNotFound = authorOptional.map(a -> "author ID: " + a.getId())
                .orElse("Такой автор не найден");
        System.out.println(authorNotFound);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> listAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public List<Genre> listGenres() {
        return genreRepository.findAll();
    }

}
