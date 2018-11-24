package ru.otus.dz31.service;

import ru.otus.dz31.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    void add(String content, Integer book);

    List<Comment> listComments();

    List<Comment> listCommentsByBook(Integer bookId);

    void updateComment(Integer id, String commentContent);

    void deleteComment(Integer id);

    Optional<Comment> findCommentById(Integer id);
}
