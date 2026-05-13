package com.example.bookapi.controller;

import com.example.bookapi.dto.ApiResponse;
import com.example.bookapi.dto.BookDTO;
import com.example.bookapi.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

   
    @GetMapping
    public ResponseEntity<ApiResponse<List<BookDTO>>> getAll() {

        List<BookDTO> books = service.getAllBooks();

        return ResponseEntity.ok(
                ApiResponse.<List<BookDTO>>builder()
                        .status("SUCCESS")
                        .code(200)
                        .message("Books fetched successfully")
                        .data(books)
                        .build()
        );
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<ApiResponse<BookDTO>> getByIsbn(@PathVariable String isbn) {

        BookDTO book = service.getBook(isbn);

        return ResponseEntity.ok(
                ApiResponse.<BookDTO>builder()
                        .status("SUCCESS")
                        .code(200)
                        .message("Book fetched successfully")
                        .data(book)
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<ApiResponse<BookDTO>> create(@Valid @RequestBody BookDTO dto) {

        BookDTO saved = service.saveBook(dto);

        return ResponseEntity.status(201).body(
                ApiResponse.<BookDTO>builder()
                        .status("SUCCESS")
                        .code(201)
                        .message("Book created successfully")
                        .data(saved)
                        .build()
        );
    }

 
    @PutMapping("/{isbn}")
    public ResponseEntity<ApiResponse<BookDTO>> update(@PathVariable String isbn,
                                                       @RequestBody BookDTO dto) {

        BookDTO updated = service.updateBook(isbn, dto);

        return ResponseEntity.ok(
                ApiResponse.<BookDTO>builder()
                        .status("SUCCESS")
                        .code(200)
                        .message("Book updated successfully")
                        .data(updated)
                        .build()
        );
    }


    @DeleteMapping("/{isbn}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable String isbn) {

        service.deleteBook(isbn);

        return ResponseEntity.ok(
                ApiResponse.<String>builder()
                        .status("SUCCESS")
                        .code(200)
                        .message("Book deleted successfully")
                        .data(null)
                        .build()
        );
    }
}