package com.pascal.libreria.repository;

import com.pascal.libreria.entity.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByIsbn(String isbn);
    List<Book> findByStatus(Book.Status status);
    List<Book> findByAuthorAndStatus(String author, Book.Status status);
    List<Book> findByAuthor(String author);

    @Query(value = "SELECT * FROM books WHERE published_date BETWEEN :startDate AND :endDate",
            nativeQuery = true)
    List<Book> findBooksByDateRange(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    @Query(value = "SELECT COUNT(*) FROM books WHERE status = 'AVAILABLE'",
            nativeQuery = true)
    Long countAvailableBooks();

    @Query(value = "SELECT COUNT(*) FROM books WHERE status = 'BORROWED'",
            nativeQuery = true)
    Long countBorrowedBooks();

    @Query(value = "SELECT * FROM books WHERE author = :author",
            nativeQuery = true)
    List<Book> findBooksByAuthor(@Param("author") String author);
}
