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
import ru.otus.dz31.domain.*;
import ru.otus.dz31.dto.CommentDto;
import ru.otus.dz31.repository.UserRepository;
import ru.otus.dz31.service.CommentService;
import ru.otus.dz31.service.LibraryService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CommentController.class)
@WithMockUser(
        username = "user"
)
public class CommentControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private LibraryService libraryService;

    @MockBean
    private CommentService commentService;

    @MockBean
    private UserRepository userRepository;

    @Configuration
    @ComponentScan(basePackageClasses = {CommentController.class})
    public static class TestConf {
    }

    private Comment comment;
    private Author author;
    private Genre genre;
    private Book book;
    private List<Comment> comments;
    private CommentDto commentDto;

    @Before
    public void setUp() throws Exception {
        author = new Author("Лев", "Толстой");
        author.setId(1);
        genre = new Genre("роман-эпопея");
        genre.setId(1);
        book = new Book("Война и мир", author, genre);
        book.setId(1);
        comment = new Comment("Эпично, но слишком затянуто.", book);
        comment.setId(1);
        comments = Arrays.asList(comment);
        commentDto = new CommentDto(1, "Очень понравилось","Война и мир",1);
    }

    @Test
    public void commentsByBookPage() throws Exception {
        Mockito.when(libraryService.findBookById(1)).thenReturn(Optional.of(book));
        Mockito.when(commentService.listCommentsByBook(book.getId())).thenReturn(comments);
        mvc.perform(get("/comments?book-id=" + book.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void saveComment() throws Exception {
        mvc.perform(post("/comments/add")
                .flashAttr("commentDto", commentDto))
                .andExpect(redirectedUrl("/comments?book-id=1"));
    }

    @Test
    public void updateComment() throws Exception {
        mvc.perform(post("/comments/add")
                .flashAttr("commentDto", commentDto))
                .andExpect(redirectedUrl("/comments?book-id=1"));
    }

    @Test
    public void deleteComment() throws Exception {
        mvc.perform(post("/comments/add")
                .flashAttr("commentDto", commentDto))
                .andExpect(redirectedUrl("/comments?book-id=1"));
    }

    @Test
    public void addCommentPage() throws Exception {
        mvc.perform(get("/addcomment?id=" + book.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void editCommentPage() throws Exception {
        Mockito.when(commentService.findCommentById(1)).thenReturn(Optional.of(comment));
        mvc.perform(get("/addcomment/edit?id=" + comment.getId()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(comment.getContent())));
    }
}