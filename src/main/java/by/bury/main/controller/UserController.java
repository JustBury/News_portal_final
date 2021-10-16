package by.bury.main.controller;

import by.bury.main.entity.User;
import by.bury.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class UserController {

    private static final String USER ="user";

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder bcryptBean;

    @RequestMapping("/registration")
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

}
