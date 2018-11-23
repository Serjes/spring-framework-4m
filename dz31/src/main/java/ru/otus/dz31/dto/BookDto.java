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

//    public BookDto() {
//    }

//    public BookDto(Integer id, String bookTitle, String authorName, String authorLastName, String genre) {
//        this.id = id;
//        this.bookTitle = bookTitle;
//        this.authorName = authorName;
//        this.authorLastName = authorLastName;
//        this.genre = genre;
//    }

//    public Integer getId() {
//        return id;
//    }
//
//    public String getBookTitle() {
//        return bookTitle;
//    }
//
//    public String getAuthorName() {
//        return authorName;
//    }
//
//    public String getAuthorLastName() {
//        return authorLastName;
//    }
//
//    public String getGenre() {
//        return genre;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public void setBookTitle(String bookTitle) {
//        this.bookTitle = bookTitle;
//    }
//
//    public void setAuthorName(String authorName) {
//        this.authorName = authorName;
//    }
//
//    public void setAuthorLastName(String authorLastName) {
//        this.authorLastName = authorLastName;
//    }
//
//    public void setGenre(String genre) {
//        this.genre = genre;
//    }

    public static BookDto toDto(Book book){
        return new BookDto(book.getId(), book.getTitle(), book.getAuthor().getFirstName(),
                book.getAuthor().getLastName(), book.getGenre().getName());
    }
}
