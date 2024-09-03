package com.pascal.libreria.controller;

import com.pascal.libreria.entity.Book;
import com.pascal.libreria.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


@RestController
@RequestMapping("/v1/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
        Book savedBook = bookService.createBook(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getBooks(
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String status) {
        List<Book> books = bookService.getBooks(author, status);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Long id) {
        Book book = bookService.getBookById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable("id") Long id, @Valid @RequestBody Book book) {
        Book updatedBook = bookService.updateBook(id, book);
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable("id") Long id) {
        String response = bookService.deleteBookById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/author")
    public ResponseEntity<List<Book>> getBooksByAuthor(@RequestParam("author") String author) {
        List<Book> books = bookService.findBooksByAuthor(author);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/date-range")
    public ResponseEntity<List<Book>> getBooksByDateRange(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // Convert user input to LocalDate
        LocalDate startLocalDate = LocalDate.parse(startDate, formatter);
        LocalDate endLocalDate = LocalDate.parse(endDate, formatter);

        List<Book> books = bookService.findBooksByDateRange(startLocalDate, endLocalDate);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/count/available")
    public ResponseEntity<Long> countAvailableBooks() {
        long count = bookService.countAvailableBooks();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/count/borrowed")
    public ResponseEntity<Long> countBorrowedBooks() {
        long count = bookService.countBorrowedBooks();
        return ResponseEntity.ok(count);
    }
}
