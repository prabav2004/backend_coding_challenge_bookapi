package com.example.bookapi.service;

import com.example.bookapi.dto.BookDTO;
import com.example.bookapi.entity.Book;
import com.example.bookapi.exception.ResourceNotFoundException;
import com.example.bookapi.mapper.BookMapper;
import com.example.bookapi.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository repo;

    public BookService(BookRepository repo) {
        this.repo = repo;
    }

    // GET ALL
    public List<BookDTO> getAllBooks() {
        return repo.findAll()
                .stream()
                .map(BookMapper::toDTO)
                .collect(Collectors.toList());
    }

    // GET BY ISBN
    public BookDTO getBook(String isbn) {
    Book book = repo.findById(isbn)
            .orElseThrow(() ->
                new ResourceNotFoundException("Book not found with ISBN: " + isbn)
            );

    return BookMapper.toDTO(book);
}

    // CREATE
    public BookDTO saveBook(BookDTO dto) {
        Book book = BookMapper.toEntity(dto);
        return BookMapper.toDTO(repo.save(book));
    }

    // UPDATE
    public BookDTO updateBook(String isbn, BookDTO dto) {
        Book existing = repo.findById(isbn)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        existing.setTitle(dto.getTitle());
        existing.setAuthor(dto.getAuthor());
        existing.setPublicationYear(dto.getPublicationYear());

        return BookMapper.toDTO(repo.save(existing));
    }

    // DELETE
    public void deleteBook(String isbn) {
        repo.deleteById(isbn);
    }
}