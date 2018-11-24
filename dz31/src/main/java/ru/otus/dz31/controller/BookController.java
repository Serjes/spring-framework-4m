package ru.otus.dz31.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.otus.dz31.domain.Book;
import ru.otus.dz31.dto.BookDto;
import ru.otus.dz31.service.LibraryService;

import java.util.List;
import java.util.Optional;

@Controller
public class BookController {

    private final LibraryService libraryService;

    @Autowired
    public BookController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/books")
    public String booksPage(Model model) {
        List<Book> books = libraryService.listBooks();
        model.addAttribute("books", books);
        BookDto bookDto = new BookDto();
        model.addAttribute("bookDto", bookDto);
        return "books";
    }

    @PostMapping("/books/delete/")
    public String delete(
            @ModelAttribute("bookDto") BookDto bookDto
    ) {
        libraryService.delBook(bookDto.getId());
        return "redirect:/books";
    }

    @PostMapping("/books/add")
    public String saveBook(
            @ModelAttribute("bookDto") BookDto bookDto
    ) {
        libraryService.addBook(bookDto.getBookTitle(),  bookDto.getAuthorName(),
                bookDto.getAuthorLastName(), bookDto.getGenre());

        return "redirect:/books";
    }

    @PostMapping("/books/add/{id}")
    public String updateBook(
            Model model,
            @ModelAttribute("bookDto") BookDto bookDto,
            @PathVariable("id") Integer id
    ) {
        libraryService.updateBook(id, bookDto.getBookTitle(),
                bookDto.getAuthorName(), bookDto.getAuthorLastName(),
                bookDto.getGenre());
        return "redirect:/books";
    }

    @GetMapping("/addbook")
    public String addBookPage(Model model) {
        BookDto bookDto = new BookDto();
        model.addAttribute("bookDto", bookDto);
        return "addbook";
    }

    @GetMapping("/addbook/edit")
    public String editBookPage(
            @RequestParam("id") Integer id,
            Model model
    ) {
        Optional<Book> bookOptional = libraryService.findBookById(id);
        if (bookOptional.isPresent()) {
            BookDto bookDto = BookDto.toDto(bookOptional.get());
            model.addAttribute("bookDto", bookDto);
        }
        return "addbook";
    }
}
