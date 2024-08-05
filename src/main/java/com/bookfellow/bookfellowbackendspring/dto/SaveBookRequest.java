package com.bookfellow.bookfellowbackendspring.dto;

import com.bookfellow.bookfellowbackendspring.entity.Book;
import java.util.List;
import lombok.Data;

@Data
public class SaveBookRequest {
  private List<Book> books;
}
