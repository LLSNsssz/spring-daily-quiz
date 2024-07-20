package crudtest.springdailyquiz.one;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookController {

    private List<Book> books = new ArrayList<>();
    private Long nextId = 1L;

    @GetMapping("/books")
    public String bookList(Model model) {
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/addBook")
    public String newBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "addBook";
    }

    @PostMapping("/addBook")
    public String saveBook(@ModelAttribute Book book) {
        book.setId(nextId++);
        books.add(book);
        return "redirect:/books";
    }

    // 메서드 추가
    @GetMapping("/editBook/{id}")
    public String showEditBookForm(@PathVariable("id") Long id, Model model) {
        Book book = findBookById(id);
        model.addAttribute("book", book);
        return "editBook";
    }

    // 메서드 추가
    @PostMapping("/editBook/{id}")
    public String editBook(@PathVariable("id") Long id, @ModelAttribute Book updatedBook) {
        updateBook(id, updatedBook);
        return "redirect:/books";
    }

    // 메서드 추가
    private void updateBook(Long id, Book updatedBook) {
        Book book = findBookById(id);
        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setPublicationYear(updatedBook.getPublicationYear());
    }

    // 메서드 추가
    private Book findBookById(Long id) {
        return books.stream().filter(b -> b.getId() == id).findFirst()
            .orElseThrow(() -> new IllegalArgumentException("해당 도서는 존재하지 않습니다."));
    }

    // 메서드 추가
    @GetMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        books.removeIf(b -> b.getId() == id);
        return "redirect:/books";
    }


}
