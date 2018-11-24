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
import ru.otus.dz31.domain.Genre;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class GenreRepositoryJpaTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private GenreRepository genreRepositoryJpa;

    @Test
    public void whenGetByName_thenReturnGenre(){
        Genre genre = new Genre("Информационные технологии");
        entityManager.persist(genre);
        entityManager.flush();

        Genre gotGenre = genreRepositoryJpa.findByName(genre.getName());

        assertThat(gotGenre.getName())
                .isEqualTo(genre.getName());
    }

}