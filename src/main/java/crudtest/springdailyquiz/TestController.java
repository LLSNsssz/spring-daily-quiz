package crudtest.springdailyquiz.controller;

import crudtest.springdailyquiz.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/index")
    public String test(Model model) {
        model.addAttribute("message", "Hello, Thymeleaf!!!");

        User seung = new User("seung", "seung@naver.com", true);
        model.addAttribute("user", seung);
        return "index";
    }

}
