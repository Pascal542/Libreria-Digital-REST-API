package com.pascal.libreria.controller;

import com.pascal.libreria.entity.Book;
import com.pascal.libreria.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/v1")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/books")
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book newbook) {
        // VALIDATIONS: isbn must be unique
        Optional<Book> existingBook = bookService.getBookByIsbn(newbook.getIsbn());
        if (existingBook.isPresent()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        // RETURN: book created
        Book savedBook = bookService.saveBook(newbook);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }
}
