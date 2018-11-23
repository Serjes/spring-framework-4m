package ru.otus.dz31.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.dz31.domain.Author;
import ru.otus.dz31.domain.Book;
import ru.otus.dz31.domain.Genre;
import ru.otus.dz31.repository.UserRepository;
import ru.otus.dz31.service.CommentService;
import ru.otus.dz31.service.LibraryService;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(GenreController.class)
@WithMockUser(
        username = "user"
)
public class GenreControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private LibraryService libraryService;

    @MockBean
    private CommentService commentService;

    @MockBean
    private UserRepository userRepository;

    @Configuration
    @ComponentScan(basePackageClasses = {GenreController.class})
    public static class TestConf {
    }

    private Author author;
    private Genre genre;
    private Book book;
    private List<Genre> genres;

    @Before
    public void setUp() throws Exception {
        author = new Author("Лев", "Толстой");
        author.setId(1);
        genre = new Genre("роман-эпопея");
        genre.setId(1);
        book = new Book("Война и мир", author, genre);
        genres = Arrays.asList(genre);
    }

    @Test
    public void commentsPage() throws Exception {
        Mockito.when(libraryService.listGenres()).thenReturn(genres);
        mvc.perform(get("/genres"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(genre.getName())))
                .andExpect(view().name("genres"));
    }
}