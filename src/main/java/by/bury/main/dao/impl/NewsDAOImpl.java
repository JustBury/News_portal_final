package by.bury.main.dao.impl;

import by.bury.main.dao.NewsDAO;
import by.bury.main.entity.News;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class NewsDAOImpl implements NewsDAO {

    private final static String NEWS_ID = "newsId";

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void saveNews(News news) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(news);
    }

    @Override
    public News getNews(int id) {
        Session session = sessionFactory.getCurrentSession();
        News news = session.get(News.class,id);
        return news;
    }

    @Override
    public List<News> getNewses() {

        Session session = sessionFactory.getCurrentSession();

        Query<News> theQuery = session.createQuery("from News", News.class);

        List<News> newses = theQuery.getResultList();

        return newses;
    }

    @Override
    public void delete(int id) {
        // get the current hibernate session
        Session session = sessionFactory.getCurrentSession();

        // delete object with primary key
        Query theQuery =
                session.createQuery("delete from News where id=:newsId");
        theQuery.setParameter(NEWS_ID, id);

        theQuery.executeUpdate();
    }
}
