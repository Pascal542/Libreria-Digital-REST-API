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
    public Book updateBook(Long id, Book book) {
        Book existingBook = getBookById(id);

        // VALIDATIONS: isbn code must be different from any in the database
        if (!existingBook.getIsbn().equals(book.getIsbn())) {
            getBookByIsbn(book);
        }
        
        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setIsbn(book.getIsbn());
        existingBook.setPublishedDate(book.getPublishedDate());
        existingBook.setStatus(book.getStatus());
        return bookRepository.save(existingBook);
    }

    @Override
    public String deleteBookById(Long id) {
        // VALIDATIONS: book must exist
        getBookById(id);

        bookRepository.deleteById(id);
        return "El libro con id " + id + " fue eliminado exitosamente";
    }

    public Optional<Book> getBookByIsbn(Book book) {
        Optional<Book> existingBook = bookRepository.findByIsbn(book.getIsbn());
        if (existingBook.isPresent()) {
            throw new BookAlreadyExistsException("Libro con codigo ISBN "
                    + book.getIsbn()
                    + " ya existe");
        }
        return existingBook;
    }

    @Override
    public Book getBookById(Long id) {
        // VALIDATIONS: book must exist
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Libro con id " + id + " no existe"));
    }

    @Override
    public List<Book> getBooks(String author, String status) {
        List<Book> books;

        if (author != null && status != null) {
            books = bookRepository.findByAuthorAndStatus(author, Book.Status.valueOf(status.toUpperCase()));
        } else if (author != null) {
            books = bookRepository.findByAuthor(author);
        } else if (status != null) {
            books =bookRepository.findByStatus(Book.Status.valueOf(status.toUpperCase()));
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
        getBookByIsbn(newBook);
        // Save and return the new book
        return bookRepository.save(newBook);
    }
}
