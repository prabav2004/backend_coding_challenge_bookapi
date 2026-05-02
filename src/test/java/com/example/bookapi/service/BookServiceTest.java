package com.example.bookapi.service;

import com.example.bookapi.dto.BookDTO;
import com.example.bookapi.entity.Book;
import com.example.bookapi.mapper.BookMapper;
import com.example.bookapi.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @Mock
    private BookRepository repo;

    @InjectMocks
    private BookService service;

    public BookServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllBooks() {

        Book book = Book.builder()
                .isbn("101")
                .title("Java")
                .author("James")
                .publicationYear(2023)
                .build();

        when(repo.findAll()).thenReturn(List.of(book));

        List<BookDTO> result = service.getAllBooks();

        assertEquals(1, result.size());
        assertEquals("Java", result.get(0).getTitle());
    }

    @Test
    void testSaveBook() {

        BookDTO dto = BookDTO.builder()
                .isbn("102")
                .title("Spring")
                .author("Rod")
                .publicationYear(2022)
                .build();

        Book entity = BookMapper.toEntity(dto);

        when(repo.save(any(Book.class))).thenReturn(entity);

        BookDTO saved = service.saveBook(dto);

        assertEquals("Spring", saved.getTitle());
    }
}