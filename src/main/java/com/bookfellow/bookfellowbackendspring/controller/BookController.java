package com.bookfellow.bookfellowbackendspring.controller;

import com.bookfellow.bookfellowbackendspring.dto.BookResponse;
import com.bookfellow.bookfellowbackendspring.dto.ErrorResponse;
import com.bookfellow.bookfellowbackendspring.entity.Book;
import com.bookfellow.bookfellowbackendspring.services.BookService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/books")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class BookController {
  private final Logger logger = LoggerFactory.getLogger(BookController.class);
  private final BookService bookService;

  @GetMapping("/find")
  public ResponseEntity<BookResponse> findBooks(
      @RequestParam(required = false) String isbn,
      @RequestParam(required = false) String title,
      @RequestParam(required = false) String author,
      @RequestParam(required = false) Integer yearOfPublication,
      @RequestParam(required = false) String publisher) {
    logger.info(
        "Fetching books with filters - ISBN: {}, Title: {}, Author: {}, Year: {}, Publisher: {}",
        isbn, title, author, yearOfPublication, publisher);

    List<Book> books =
        bookService.findBooksByParams(isbn, title, author, yearOfPublication, publisher);
    BookResponse bookResponse = new BookResponse();

    if (books.isEmpty()) {
      logger.warn("No books found with the provided filters");
      bookResponse.setError(new ErrorResponse("BOOK-001", "No books found"));
      return ResponseEntity.ok(bookResponse);
    }

    bookResponse.setBooks(books);
    logger.info("{} books found", books.size());
    return ResponseEntity.ok(bookResponse);
  }
}
