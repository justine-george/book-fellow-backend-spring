package com.bookfellow.bookfellowbackendspring.repository;

import com.bookfellow.bookfellowbackendspring.entity.Book;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, UUID> {
  Optional<Book> findByIsbn(String isbn);

  Optional<Book> findByTitle(String title);

  Optional<Book> findByAuthor(String author);

  Optional<Book> findByPublisher(String publisher);

  Optional<Book> findByYearOfPublication(Integer yearOfPublication);

  void deleteByIsbnIn(List<String> isbnList);
}
