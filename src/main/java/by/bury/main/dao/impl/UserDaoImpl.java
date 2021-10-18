package by.bury.main.dao.impl;

import by.bury.main.dao.UserDao;

import by.bury.main.entity.News;
import by.bury.main.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private static final String USER_ID = "userId";
    private static final String USER_ROLE = "userRole";

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void registration(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    @Override
    public List<User> getUsers() {
        Session session = sessionFactory.getCurrentSession();
        Query<User> theQuery = session.createQuery("from User", User.class);
        List<User> users = theQuery.getResultList();
        return users;
    }

    @Override
    public void deleteUser(int id) {
        // get the current hibernate session
        Session session = sessionFactory.getCurrentSession();

        // delete object with primary key
        Query theQuery =
                session.createQuery("delete from User where id=:userId");
        theQuery.setParameter(USER_ID, id);
        theQuery.executeUpdate();
    }

    @Override
    public void updateRoleUser(int userId, String userRole) {
        Session session = sessionFactory.getCurrentSession();
        Query theQuery = session.createQuery("update User set role = :userRole where id = :userId");
        theQuery.setParameter(USER_ID, userId);
        theQuery.setParameter(USER_ROLE, userRole);
        theQuery.executeUpdate();
    }

    @Override
    public User getUser(int id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class,id);
        return user;
    }


}
