package com.pascal.libreria.service;

import com.pascal.libreria.entity.Book;
import java.util.List;
import java.util.Optional;

public interface BookService{
    Book saveBook(Book book);
    Optional<Book> getBookById(Long id);
    List<Book> getAllBooks();
    void deleteBook(Long id);
    Optional<Book> getBookByIsbn(String isbn);
}
