package crudtest.springdailyquiz.one;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TestController {

    @GetMapping("/index")
    public String test(Model model) {
        User user = new User("lee", "lee@example.com", true, "1234");
        model.addAttribute("user", user);
        return "index";
    }

    @GetMapping("/products")
    public String listUsers(Model model) {
        List<Product> products = new ArrayList<>();
        products.add(new Product(1L, "Product 1", 10.99));
        products.add(new Product(2L, "Product 2", 20.99));
        products.add(new Product(3L, "Product 3", 30.99));
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/register")
    public String newUserForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute User user) {
        System.out.println("Received user registration");
        System.out.println("Name = " + user.getUsername());
        System.out.println("Email = " + user.getEmail());
        System.out.println("Password = " + user.getPassword());
        return "redirect:/index";
    }
}
