package ru.otus.dz31.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.otus.dz31.domain.Genre;
import ru.otus.dz31.service.LibraryService;

import java.util.List;

@Controller
public class GenreController {

    private final LibraryService libraryService;

    @Autowired
    public GenreController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/genres")
    public String commentsPage(Model model) {
        List<Genre> genreList = libraryService.listGenres();
        model.addAttribute("genres", genreList);
        return "genres";
    }

}
