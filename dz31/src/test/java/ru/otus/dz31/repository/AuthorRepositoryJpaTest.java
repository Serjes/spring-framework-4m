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

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class AuthorRepositoryJpaTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AuthorRepository authorRepositoryJpa;

    @Test
    public void whenGetByName_thenReturnAuthor(){
        Author author = new Author("Брюс", "Эккель");
        entityManager.persist(author);
        entityManager.flush();

        Optional<Author> gotAuthor = authorRepositoryJpa.findByFirstNameAndLastName(author.getFirstName(),author.getLastName());

        assertThat(gotAuthor.get().getFirstName())
                .isEqualTo(author.getFirstName());
        assertThat(gotAuthor.get().getLastName())
                .isEqualTo(author.getLastName());
    }
}