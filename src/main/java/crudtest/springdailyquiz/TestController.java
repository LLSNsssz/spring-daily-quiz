package crudtest.springdailyquiz;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class TestController {

    private List<User> users = new ArrayList<>();

//    @GetMapping("/index")
//    public String test(Model model) {
//        model.addAttribute("message", "Hello, Thymeleaf!!!");
//
//
//        model.addAttribute("users", users);
//
//        return "index";
//    }

    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", users);
        return "users/list";
    }

    @GetMapping("/new")
    public String newUserForm(Model model) {
        model.addAttribute("user", new User());
        return "users/form";
    }

    @PostMapping
    public String saveUser(@ModelAttribute User user) {
        users.add(user);
        return "redirect:/users";
    }
}
