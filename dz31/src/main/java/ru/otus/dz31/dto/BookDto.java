package ru.otus.dz31.dto;

import lombok.*;
import ru.otus.dz31.domain.Book;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuppressWarnings("all")
public class BookDto {

    private Integer id;
    private String bookTitle;
    private String authorName;
    private String authorLastName;
    private String genre;

    public static BookDto toDto(Book book){
        return new BookDto(book.getId(), book.getTitle(), book.getAuthor().getFirstName(),
                book.getAuthor().getLastName(), book.getGenre().getName());
    }
}
