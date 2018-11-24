package ru.otus.dz31.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dz31.domain.*;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepositoryJpa;

    @Autowired
    private GenreRepository genreRepositoryJpa;

    @Autowired
    private AuthorRepository authorRepositoryJpa;

    private Author author;
    private Genre genre;
    private Book book;

    @Before
    public void setUp() throws Exception {
        author = new Author("Лев", "Толстой");
        authorRepositoryJpa.save(author);
        genre = new Genre("роман-эпопея");
        genreRepositoryJpa.save(genre);
        book = new Book("Война и мир", author, genre);
        bookRepositoryJpa.save(book);
    }

    @Test
    public void findAll() {
        List<Book> books = bookRepositoryJpa.findAll();
        assertNotNull(books);
        assertEquals(book.getTitle(), books.get(0).getTitle());
    }

    @Test
    public void findById() {
        Optional<Book> optionalBook = bookRepositoryJpa.findById(book.getId());
        assertEquals(optionalBook.get().getTitle(), book.getTitle());
    }

    @Test
    public void deleteById() {
        bookRepositoryJpa.deleteById(book.getId());
        assertEquals(0, bookRepositoryJpa.count());
    }
}