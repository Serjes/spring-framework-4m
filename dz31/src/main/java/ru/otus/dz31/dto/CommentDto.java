package ru.otus.dz31.dto;

import lombok.*;
import ru.otus.dz31.domain.Comment;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentDto {

    private Integer id;
    private String commentContent;
    private String bookTitle;
    private Integer bookId;

    public static CommentDto toDto(Comment comment) {
        return new CommentDto(comment.getId(), comment.getContent(), comment.getBook().getTitle(),
                comment.getBook().getId());
    }
}
