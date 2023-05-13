package com.polarbookshop.catalogservice.web;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.domain.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class HomeController {

    private final BookService bookService;

    public HomeController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String getGreetings(){
        return "Welcome to the book catalog";
    }

    @GetMapping("/books")
    public Iterable<Book> getBooks(){
        return bookService.viewBookList();
    }

    @GetMapping("/books/{isbn}")
    public Book getBookByIsbn(@PathVariable String isbn){
        return bookService.viewBookDetails(isbn);
    }

    @PostMapping("/books")
    @ResponseStatus(HttpStatus.CREATED)
    public Book saveBook(@Valid @RequestBody Book book){
        return bookService.addBookToCatalog(book);
    }

    @DeleteMapping("/books/{isbn}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeBook(@PathVariable String isbn){
        bookService.removeBookFromCatalog(isbn);
    }

    @PutMapping("/books/{isbn}")
    public Book editBook(@PathVariable String isbn, @Valid @RequestBody Book newBookDetails){
        return bookService.editBookDetails(isbn, newBookDetails);
    }
}
