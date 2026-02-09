package com.library.chapter.api.controller;

import com.library.chapter.api.dto.copy.BookCopyRequestDTO;
import com.library.chapter.api.dto.copy.BookCopyResponseDTO;
import com.library.chapter.application.service.BookCopyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/book-copies")
public class BookCopyController {

    private final BookCopyService bookCopyService;
    public BookCopyController(BookCopyService bookCopyService) {
        this.bookCopyService = bookCopyService;
    }

    // CREATE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BookCopyResponseDTO createBookCopy(@Valid @RequestBody BookCopyRequestDTO dto) {
        return bookCopyService.createBookCopy(dto);
    }

    // READ
    @GetMapping
    public List<BookCopyResponseDTO> getAllBookCopies() {
        return bookCopyService.getAllBookCopies();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public BookCopyResponseDTO getBookCopyById(@PathVariable Long id) {
        return bookCopyService.getBookCopyById(id);
    }

    // UPDATE
    @PostMapping("/{id}")
    public BookCopyResponseDTO updateBookCopy(@PathVariable Long id, @Valid @RequestBody BookCopyRequestDTO dto) {
        return bookCopyService.updateBookCopy(id, dto);
    }

    // DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBookCopy(@PathVariable Long id) {
        bookCopyService.deleteBookCopy(id);
    }
}
