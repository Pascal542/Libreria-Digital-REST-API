package com.pascal.libreria.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Objects;

// Representa un libro de la libreria
// es el producto principal del negocio

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books", uniqueConstraints = @UniqueConstraint(columnNames = "isbn"))
public class Book {
    @Id
    @GeneratedValue( strategy = javax.persistence.GenerationType.IDENTITY )
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "El titulo no puede estar vacio")
    private String title;

    @Column(nullable = false)
    @NotBlank(message = "El autor no puede estar vacio")
    private String author;

    // Codigo unico para identificar un libro (ISBN)
    @Column(nullable = false, unique = true, length = 17)
    @NotBlank(message = "El codigo isbn no puede ser vacio")
    @Size(min = 10, max = 17, message = "El codigo isbn debe tener entre 10 y 17 caracteres")
    private String isbn;

    @Column(name = "published_date", nullable = false)
    @PastOrPresent(message = "La fecha debe ser anterior o igual a la actual")
    private LocalDate publishedDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    // Enum representando los estados de un libro
    // dentro de la tienda digital
    public enum Status {
        AVAILABLE,
        BORROWED
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(isbn, book.isbn) && Objects.equals(publishedDate, book.publishedDate) && status == book.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, isbn, publishedDate, status);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publishedDate=" + publishedDate +
                ", status=" + status +
                '}';
    }
}
