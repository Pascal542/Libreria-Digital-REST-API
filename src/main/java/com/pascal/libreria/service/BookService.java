package com.pascal.libreria.service;

import com.pascal.libreria.entity.Book;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BookService{
    Book updateBook(Long id, Book book);
    Book createBook(Book book);
    String deleteBookById(Long id);
    List<Book> findBooksByAuthor(String author);
    List<Book> findBooksByDateRange(LocalDate startDate, LocalDate endDate);
    Long countAvailableBooks();
    Long countBorrowedBooks();
    List<Book> getBooks(String author, String status);
    Book getBookById(Long id);
    LocalDate getPublishedDate(Book book);
    Optional<Book> getBookByIsbn(Book book);

}
