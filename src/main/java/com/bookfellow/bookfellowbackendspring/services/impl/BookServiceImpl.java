package com.bookfellow.bookfellowbackendspring.services.impl;

import com.bookfellow.bookfellowbackendspring.entity.Book;
import com.bookfellow.bookfellowbackendspring.repository.BookRepository;
import com.bookfellow.bookfellowbackendspring.services.BookService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
  private final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);
  private final BookRepository bookRepository;

  @Override
  public void saveBook(Book book) {
    logger.info("Adding book: {}", book);
    bookRepository.save(book);
    logger.info("Book added: {}", book);
  }

  @Override
  public void saveBooks(List<Book> books) {
    logger.info("Saving {} book(s)", books.size());
    bookRepository.saveAll(books);
    logger.info("{} book(s) saved", books.size());
  }

  @Override
  public void deleteBook(Book book) {
    logger.info("Deleting book: {}", book);
    bookRepository.delete(book);
    logger.info("Book deleted: {}", book);
  }

  @Override
  public void deleteBooks(List<String> isbnList) {
    logger.info("Deleting {} book(s)", isbnList.size());
    bookRepository.deleteByIsbnIn(isbnList);
    logger.info("{} book(s) deleted", isbnList.size());
  }

  @Override
  public List<Book> getAllBooks() {
    logger.info("Getting all books");
    return bookRepository.findAll();
  }

  public List<Book> findBooksByParams(String isbn, String title, String author,
                                      Integer yearOfPublication,
                                      String publisher) {
    logger.info(
        "Fetching books with filters - ISBN: {}, Title: {}, Author: {}, Year: {}, Publisher: {}",
        isbn, title, author, yearOfPublication, publisher);
    return bookRepository.findAll().stream()
        .filter(book -> (isbn == null || book.getIsbn().equals(isbn)) &&
            (title == null || book.getTitle().equalsIgnoreCase(title)) &&
            (author == null || book.getAuthor().equalsIgnoreCase(author)) &&
            (yearOfPublication == null || book.getYearOfPublication().equals(yearOfPublication)) &&
            (publisher == null || book.getPublisher().equalsIgnoreCase(publisher)))
        .collect(Collectors.toList());
  }

  @Override
  public Book getBookByIsbn(String isbn) {
    logger.info("Getting book by ISBN: {}", isbn);
    return bookRepository.findByIsbn(isbn).orElse(null);
  }

  @Override
  public Book getBookByTitle(String title) {
    logger.info("Getting book by title: {}", title);
    return bookRepository.findByTitle(title).orElse(null);
  }

  @Override
  public Book getBookByAuthor(String author) {
    logger.info("Getting book by author: {}", author);
    return bookRepository.findByAuthor(author).orElse(null);
  }

  @Override
  public Book getBookByPublisher(String publisher) {
    logger.info("Getting book by publisher: {}", publisher);
    return bookRepository.findByPublisher(publisher).orElse(null);
  }

  @Override
  public Book getBookByYearOfPublication(Integer yearOfPublication) {
    logger.info("Getting book by year of publication: {}", yearOfPublication);
    return bookRepository.findByYearOfPublication(yearOfPublication).orElse(null);
  }
}
