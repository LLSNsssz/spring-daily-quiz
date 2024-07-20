package crudtest.springdailyquiz.two;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class BookService {

    private List<Book> books = new ArrayList<>();
    private Long nextId = 1L;

    private BookDto convertToBookDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setIsbn(book.getIsbn());
        bookDto.setPrice(book.getPrice());
        bookDto.setPublishedYear(book.getPublishedYear());
        return bookDto;
    }

    private Book convertToBookEntity(BookDto bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setIsbn(bookDto.getIsbn());
        book.setPrice(bookDto.getPrice());
        book.setPublishedYear(bookDto.getPublishedYear());
        return book;
    }

    private Book findBookById(Long id) {
        return books.stream()
            .filter(b -> b.getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("그런 책 없습니다"));
    }

    public List<BookDto> getAllBooksDto() {
        return books.stream().map(this::convertToBookDto).collect(Collectors.toList());
    }

    public BookDto getBookDtoById(Long id) {
        Book book = findBookById(id);
        return convertToBookDto(book);
    }

    public BookDto createBookDto(BookDto bookDto) {
        Book book = convertToBookEntity(bookDto);
        book.setId(nextId++);
        books.add(book);
        return convertToBookDto(book);
    }

    public BookDto updateBookDto(Long id, Book updateBook) {
        Book book = findBookById(id);
        book.setTitle(updateBook.getTitle());
        book.setAuthor(updateBook.getAuthor());
        book.setIsbn(updateBook.getIsbn());
        book.setPrice(updateBook.getPrice());
        book.setPublishedYear(updateBook.getPublishedYear());

        return convertToBookDto(book);
    }

    public boolean deleteBook(Long id) {
        return books.remove(findBookById(id));
    }
}
