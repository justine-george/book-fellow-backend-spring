package com.bookfellow.bookfellowbackendspring.controller;

import com.bookfellow.bookfellowbackendspring.dto.DeleteBookRequest;
import com.bookfellow.bookfellowbackendspring.dto.SaveBookRequest;
import com.bookfellow.bookfellowbackendspring.services.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AdminController {
  private final Logger logger = LoggerFactory.getLogger(AdminController.class);
  private final BookService bookService;

  @GetMapping
  public ResponseEntity<String> sayHello() {
    return ResponseEntity.ok("Hello Admin");
  }

  @PostMapping("/save-books")
  public ResponseEntity<String> saveBooks(@Valid @RequestBody SaveBookRequest bookRequest) {
    if (bookRequest.getBooks().isEmpty()) {
      logger.warn("No books received in saveBooks request");
      return ResponseEntity.badRequest().body("No books received");
    }
    logger.info("{} book(s) received for saving", bookRequest.getBooks().size());
    try {
      bookService.saveBooks(bookRequest.getBooks());
      logger.info("Books saved successfully");
      return ResponseEntity.status(HttpStatus.CREATED).body("Books saved successfully");
    } catch (Exception e) {
      logger.error("Error saving books", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving books");
    }
  }

  @PostMapping("/delete-books")
  public ResponseEntity<String> deleteBooks(@RequestBody DeleteBookRequest bookRequest) {
    if (bookRequest.getIsbnList().isEmpty()) {
      logger.warn("No books received in deleteBooks request");
      return ResponseEntity.badRequest().body("No books received");
    }
    logger.info("{} book(s) received for deletion", bookRequest.getIsbnList().size());
    try {
      bookService.deleteBooks(bookRequest.getIsbnList());
      logger.info("Books deleted successfully");
      return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Books deleted successfully");
    } catch (Exception e) {
      logger.error("Error deleting books", e);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting books");
    }
  }
}
