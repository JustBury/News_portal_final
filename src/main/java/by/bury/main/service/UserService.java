package by.bury.main.service;

import by.bury.main.entity.User;

import java.util.List;

public interface UserService {
    public void registration(User user);

    public List<User> getUsers();

    public void deleteUser(int id);

    public void updateRoleUser(int userId, String userRole);

    public User getUser(int id);
}
