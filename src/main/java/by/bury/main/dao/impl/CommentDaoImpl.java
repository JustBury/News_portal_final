package by.bury.main.dao.impl;

import by.bury.main.dao.CommentDAO;
import by.bury.main.entity.Comment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class CommentDaoImpl implements CommentDAO {


    public static final String NEWS_ID = "idNews";
    public static final String GET_COMMENTS_BY_ID_NEWS = "from Comment where id_News=:idNews";
    public static final String DELETE_COMMENTS_BY_ID = "delete from Comment where id=:commentId";
    public static final String COMMENT_ID = "commentId";

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveComment(Comment comment) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(comment);
    }

    @Override
    public List<Comment> getComments(int idNews) {
        Session session = sessionFactory.getCurrentSession();

        Query<Comment> theQuery = session.createQuery(GET_COMMENTS_BY_ID_NEWS);
        theQuery.setParameter(NEWS_ID, idNews);

        List<Comment> comments = theQuery.getResultList();
        return comments;
    }

    @Override
    public void deleteComment(int id) {

        Session session = sessionFactory.getCurrentSession();

        Query theQuery =
                session.createQuery(DELETE_COMMENTS_BY_ID);
        theQuery.setParameter(COMMENT_ID, id);

        theQuery.executeUpdate();
    }
}
