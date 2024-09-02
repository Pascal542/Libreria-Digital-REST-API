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
    List<Book> findByAuthor(String author);
    List<Book> findByStatus(Book.Status status);
    List<Book> findByAuthorAndStatus(String author, Book.Status status);

    @Query(value = "SELECT * FROM books WHERE author = :author AND published_date BETWEEN :startDate AND :endDate",
            nativeQuery = true)
    List<Book> findBooksByAuthorAndPublicationDateRange(
            @Param("author") String author,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);

    @Query(value = "SELECT COUNT(*) FROM books WHERE status = 'AVAILABLE'",
            nativeQuery = true)
    long countAvailableBooks();

    @Query(value = "SELECT COUNT(*) FROM books WHERE status = 'BORROWED'",
            nativeQuery = true)
    long countBorrowedBooks();
}
