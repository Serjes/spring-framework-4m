package ru.otus.dz31.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dz31.domain.Author;
import ru.otus.dz31.domain.Book;
import ru.otus.dz31.domain.Genre;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class BookRepositoryJpaTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookRepository bookRepositoryJpa;

    @Autowired
    private GenreRepository genreRepositoryJpa;

    @Autowired
    private AuthorRepository authorRepositoryJpa;

    @Test
    public void whenGetById_thenReturnBook(){

        Author author = new Author("Брюс", "Эккель");
        authorRepositoryJpa.save(author);
        Genre genre = new Genre("Информационные технологии");
        genreRepositoryJpa.save(genre);

        Book book = new Book("Филиософия Java", author, genre);
        entityManager.persist(book);
        entityManager.flush();

        Optional<Book> optionalBook = bookRepositoryJpa.findById(1);
        assertEquals(optionalBook.get().getTitle(), book.getTitle());

    }

}