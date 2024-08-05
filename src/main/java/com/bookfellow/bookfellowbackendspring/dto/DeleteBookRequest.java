package com.bookfellow.bookfellowbackendspring.dto;

import java.util.List;
import lombok.Data;

@Data
public class DeleteBookRequest {
  private List<String> isbnList;
}
