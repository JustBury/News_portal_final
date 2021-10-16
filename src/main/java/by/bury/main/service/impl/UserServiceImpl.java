package by.bury.main.service.impl;

import by.bury.main.dao.UserDao;
import by.bury.main.entity.User;
import by.bury.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public void registration(User user) {
        userDao.registration(user);
    }
}
