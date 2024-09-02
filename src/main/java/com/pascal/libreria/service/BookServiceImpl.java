package com.pascal.libreria.service;

import com.pascal.libreria.entity.Book;
import com.pascal.libreria.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    public Optional<Book> getBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    @Override
    public List<Book> getBooks(String author, String status) {
        if (author != null && status != null) {
            return bookRepository.findByAuthorAndStatus(author, Book.Status.valueOf(status));
        } else if (author != null) {
            return bookRepository.findByAuthor(author);
        } else if (status != null) {
            return bookRepository.findByStatus(Book.Status.valueOf(status));
        } else {
            return bookRepository.findAll();
        }
    }
}
