package by.bury.main.service;

import by.bury.main.entity.Comment;

import java.util.List;

public interface CommentService {

    public void saveComment(Comment comment);

    public List<Comment> getComments(int idNews);

    public void deleteComment(int id);
}
