package by.bury.main.controller;

import by.bury.main.entity.News;
import by.bury.main.entity.User;
import by.bury.main.service.NewsService;
import by.bury.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdministratorController {

    private final static String NEWSES = "newses";
    private final static String USERS = "users";

    @Autowired
    private UserService userService;

    @Autowired
    private NewsService newsService;

    @RequestMapping("/list-user")
    public String getUsers(Model model) {
        List<User> users = userService.getUsers();
        model.addAttribute(USERS, users);
        return "admin-page-with-list-users";
    }

    @RequestMapping("/list-news")
    public String getNewses(Model model) {
        List<News> newses = newsService.getNewses();
        model.addAttribute(NEWSES, newses);
        return "error-page";
    }
}
