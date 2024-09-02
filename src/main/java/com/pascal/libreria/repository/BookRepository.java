package com.pascal.libreria.repository;

import com.pascal.libreria.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByIsbn(String isbn);
    List<Book> findByAuthor(String author);
    List<Book> findByStatus(Book.Status status);
    List<Book> findByAuthorAndStatus(String author, Book.Status status);
}
