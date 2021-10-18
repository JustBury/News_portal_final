package by.bury.main.controller;

import by.bury.main.entity.Comment;
import by.bury.main.entity.News;
import by.bury.main.entity.User;
import by.bury.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class UserController {

    private static final String USER ="user";
    private static final String USER_ID = "userId";

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder bcryptBean;

    @PostMapping("/registration")
    public String registrationUser(Model model){
        User user = new User();
        model.addAttribute(USER,user);
        return "registration_page";
    }

    @PostMapping("/saveUser")
    public String saveUser(@Valid @ModelAttribute(USER) User user, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "registration_page";
        } else {
            user.setPassword(bcryptBean.encode(user.getPassword()));
            userService.registration(user);
            return "redirect:/";
        }
    }
    @RequestMapping("deleteUser")
    public String deleteUser(@ModelAttribute(USER_ID) int id){
        userService.deleteUser(id);
        return "redirect:/admin/list-user";
    }

    @RequestMapping("/getUser")
    public String getNews(@ModelAttribute(USER_ID) int id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute(USER, user);
        return "user-profile";
    }

    @PostMapping("/updateRoleUser")
    public String updateUser(@ModelAttribute(USER) User user){
        userService.updateRoleUser(user.getId(),user.getRole());
        return  "redirect:/admin/list-user";
    }


}
