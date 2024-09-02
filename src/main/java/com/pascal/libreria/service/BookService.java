package com.pascal.libreria.service;

import com.pascal.libreria.entity.Book;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookService{
    Book updateBook(Long id, Book book);
    Book getBookById(Long id);
    String deleteBookById(Long id);
    Optional<Book> getBookByIsbn(Book book);
    List<Book> getBooks(String author, String status);
    Book createBook(Book book);
    LocalDate getPublishedDate(Book book);
    List<Book> findBooksByAuthor(String author);
    List<Book> findBooksByDateRange(LocalDate startDate, LocalDate endDate);
    Long countAvailableBooks();
    Long countBorrowedBooks();
}
