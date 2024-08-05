package com.bookfellow.bookfellowbackendspring.dto;

import com.bookfellow.bookfellowbackendspring.entity.Book;
import java.util.List;
import lombok.Data;

@Data
public class BookResponse {
  private List<Book> books;
  private ErrorResponse error;
}
