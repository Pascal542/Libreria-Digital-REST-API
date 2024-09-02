package com.pascal.libreria.service;

import com.pascal.libreria.entity.Book;
import com.pascal.libreria.exception.BookAlreadyExistsException;
import com.pascal.libreria.exception.BookNotFoundException;
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
    public Book getBookById(Long id) {
        // VALIDATIONS: book must exist
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Libro con id " + id + " no existe"));
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
        List<Book> books;

        if (author != null && status != null) {
            books = bookRepository.findByAuthorAndStatus(author, Book.Status.valueOf(status));
        } else if (author != null) {
            books = bookRepository.findByAuthor(author);
        } else if (status != null) {
            books =bookRepository.findByStatus(Book.Status.valueOf(status));
        } else {
            books =bookRepository.findAll();
        }

        if (books.isEmpty()) {
            throw new BookNotFoundException("No se encontraron libros");
        }

        return books;
    }

    public Book createBook(Book newBook) {
        // VALIDATIONS: isbn must be unique
        Optional<Book> existingBook = bookRepository.findByIsbn(newBook.getIsbn());
        if (existingBook.isPresent()) {
            throw new BookAlreadyExistsException("Libro con codigo ISBN "
                                                + newBook.getIsbn()
                                                + " ya existe");
        }
        // Save and return the new book
        return bookRepository.save(newBook);
    }
}
