package by.bury.main.dao;

import by.bury.main.entity.Comment;

import java.util.List;

public interface CommentDAO {

    public void saveComment(Comment comment);

    public List<Comment> getComments(int idNews);

    public void deleteComment(int id);
}
