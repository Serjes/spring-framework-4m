package ru.otus.dz31.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.otus.dz31.domain.Book;
import ru.otus.dz31.domain.Comment;
import ru.otus.dz31.dto.CommentDto;
import ru.otus.dz31.service.CommentService;
import ru.otus.dz31.service.LibraryService;

import java.util.List;
import java.util.Optional;

@Controller
public class CommentController {

    private final CommentService commentService;
    private final LibraryService libraryService;

    @Autowired
    public CommentController(CommentService commentService, LibraryService libraryService) {
        this.commentService = commentService;
        this.libraryService = libraryService;
    }

    @GetMapping("/comments")
    public String commentsByBookPage(
            @RequestParam("book-id") Integer id,
            Model model
    ) {
        Optional<Book> optionalBook = libraryService.findBookById(id);
        if (optionalBook.isPresent()) {
            List<Comment> allByBook = commentService.listCommentsByBook(id);
            model.addAttribute("comments", allByBook);
            model.addAttribute("bookTitle", optionalBook.get().getTitle());
            CommentDto commentDto = new CommentDto();
            commentDto.setBookId(id);
            model.addAttribute("commentDto", commentDto);
            model.addAttribute("bookId", optionalBook.get().getId());
        }
        return "comments";
    }

    @PostMapping("/comments/add")
    public String saveComment(
            @ModelAttribute("commentDto") CommentDto commentDto
    ) {
        commentService.add(commentDto.getCommentContent(), commentDto.getBookId());
        return "redirect:/comments?book-id=" + commentDto.getBookId();
    }

    @PostMapping("/comments/add/{id}")
    public String updateComment(
            @ModelAttribute("commentDto") CommentDto commentDto,
            @PathVariable("id") Integer id
    ) {
        commentService.updateComment(id, commentDto.getCommentContent());
        return "redirect:/comments?book-id=" + commentDto.getBookId();
    }

    @PostMapping("/comments/delete/")
    public String deleteComment(
            @ModelAttribute("commentDto") CommentDto commentDto
    ) {
        Optional<Comment> optionalComment = commentService.findCommentById(commentDto.getId());
        int id = optionalComment.get().getBook().getId();
        commentService.deleteComment(commentDto.getId());
        return "redirect:/comments?book-id=" + id;
    }

    @GetMapping("/addcomment")
    public String addCommentPage(
            Model model,
            @RequestParam("id") Integer id
    ) {
        CommentDto commentDto = new CommentDto();
        Optional<Book> optionalBook = libraryService.findBookById(id);
        if (optionalBook.isPresent()){
            commentDto.setBookTitle(optionalBook.get().getTitle());
            commentDto.setBookId(optionalBook.get().getId());
        }
        model.addAttribute("commentDto", commentDto);
        model.addAttribute("idBook", id);
        return "addcomment";
    }

    @GetMapping("/addcomment/edit")
    public String editCommentPage(
            @RequestParam("id") Integer id,
            Model model
    ) {
        CommentDto commentDto = new CommentDto();
        Optional<Comment> optionalComment = commentService.findCommentById(id);
        if(optionalComment.isPresent()) {
            commentDto.setCommentContent(optionalComment.get().getContent());
            commentDto.setBookId(optionalComment.get().getBook().getId());
            commentDto.setId(optionalComment.get().getId());
            model.addAttribute("idBook", optionalComment.get().getBook().getId());
        }
        model.addAttribute("commentDto", commentDto);

        return "addcomment";
    }
}
