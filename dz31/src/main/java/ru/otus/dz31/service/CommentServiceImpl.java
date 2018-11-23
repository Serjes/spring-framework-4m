package ru.otus.dz31.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.dz31.domain.Book;
import ru.otus.dz31.domain.Comment;
import ru.otus.dz31.repository.BookRepository;
import ru.otus.dz31.repository.CommentRepository;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public void add(String content, Integer bookId) {
        Optional<Book> bookOp = bookRepository.findById(bookId);
        if(bookOp.isPresent()){
            Comment comment = new Comment(content, bookOp.get());
            commentRepository.save(comment);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> listComments() {
        return commentRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> listCommentsByBook(Integer bookId) {

        Optional<Book> bookOp = bookRepository.findById(bookId);
        if (bookOp.isPresent()){
            return commentRepository.findAllByBook(bookOp.get());
        }
        return null;
    }

    @Override
    public void updateComment(Integer id, String commentContent) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (optionalComment.isPresent()){
            Comment comment = optionalComment.get();
            comment.setContent(commentContent);
            commentRepository.save(comment);
        }
    }

    @Override
    public void deleteComment(Integer id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        optionalComment.ifPresent(comment -> commentRepository.delete(comment));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Comment> findCommentById(Integer id) {
        return commentRepository.findById(id);
    }
}
