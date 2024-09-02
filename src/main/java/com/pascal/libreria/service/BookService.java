package com.pascal.libreria.service;

import com.pascal.libreria.entity.Book;
import java.util.List;
import java.util.Optional;

public interface BookService{
    Book saveBook(Book book);
    Book getBookById(Long id);
    void deleteBookById(Long id);
    Optional<Book> getBookByIsbn(String isbn);
    List<Book> getBooks(String author, String status);
    Book createBook(Book book);
}
