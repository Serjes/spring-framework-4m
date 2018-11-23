package ru.otus.dz31.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.dz31.domain.Author;
import ru.otus.dz31.service.LibraryService;

import java.util.List;

@Controller
public class AuthorController {

    private final LibraryService libraryService;

    @Autowired
    public AuthorController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/authors")
    public String commentsPage(Model model) {
        List<Author> authors = libraryService.listAuthors();
        model.addAttribute("authors", authors);
        return "authors";
    }

}
