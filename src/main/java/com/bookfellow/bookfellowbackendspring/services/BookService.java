package com.bookfellow.bookfellowbackendspring.services;

import com.bookfellow.bookfellowbackendspring.entity.Book;
import java.util.List;

public interface BookService {
  void saveBook(Book book);

  void saveBooks(List<Book> books);

  void deleteBook(Book book);

  void deleteBooks(List<String> isbnList);

  List<Book> findBooksByParams(String isbn, String title, String author, Integer yearOfPublication,
                       String publisher);

  List<Book> getAllBooks();

  Book getBookByIsbn(String isbn);

  Book getBookByTitle(String title);

  Book getBookByAuthor(String author);

  Book getBookByPublisher(String publisher);

  Book getBookByYearOfPublication(Integer yearOfPublication);

}
