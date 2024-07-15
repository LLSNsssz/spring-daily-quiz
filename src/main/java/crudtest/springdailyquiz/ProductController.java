package crudtest.springdailyquiz;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

    private List<Product> products = new ArrayList<>();

    @GetMapping("/products")
    public String listProducts(Model model) {
        products.add(new Product(1L, "티라미수", 9000));
        products.add(new Product(2L, "티라미수 커피", 7000));
        products.add(new Product(3L, "티라미수 쿠키", 3000));
        model.addAttribute("products", products);
        return "product/list";
    }
}
