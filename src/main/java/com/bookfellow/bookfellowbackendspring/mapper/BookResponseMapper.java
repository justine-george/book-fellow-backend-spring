package com.bookfellow.bookfellowbackendspring.mapper;

import com.bookfellow.bookfellowbackendspring.dto.BookResponse;
import com.bookfellow.bookfellowbackendspring.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface BookResponseMapper {
//  @Mapping(target = "bookId", source = "book.id")
//  @Mapping(target = "bookTitle", source = "book.title")
//  BookResponse toBookResponse(Book book);
//
//  @Mapping(target = "id", source = "dto.bookId")
//  @Mapping(target = "title", source = "dto.bookTitle")
//  Book toBook(BookResponse dto);
}
