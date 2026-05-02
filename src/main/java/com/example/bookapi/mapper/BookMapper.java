package com.example.bookapi.mapper;

import com.example.bookapi.dto.BookDTO;
import com.example.bookapi.entity.Book;

public class BookMapper {

    public static Book toEntity(BookDTO dto) {
        return Book.builder()
                .isbn(dto.getIsbn())
                .title(dto.getTitle())
                .author(dto.getAuthor())
                .publicationYear(dto.getPublicationYear())
                .build();
    }

    public static BookDTO toDTO(Book book) {
        return BookDTO.builder()
                .isbn(book.getIsbn())
                .title(book.getTitle())
                .author(book.getAuthor())
                .publicationYear(book.getPublicationYear())
                .build();
    }
}