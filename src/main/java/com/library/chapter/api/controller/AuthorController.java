package com.library.chapter.api.controller;

import com.library.chapter.api.dto.author.AuthorRequestDTO;
import com.library.chapter.api.dto.author.AuthorResponseDTO;
import com.library.chapter.application.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    // CREATE
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorResponseDTO createAuthor(@Valid @RequestBody AuthorRequestDTO author) {
        return authorService.createAuthor(author);
    }

    // READ
    @GetMapping
    public List<AuthorResponseDTO> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public AuthorResponseDTO getAuthorById(@PathVariable Long id) {
        return authorService.getAuthorById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public AuthorResponseDTO updateAuthor(@PathVariable Long id, @Valid @RequestBody AuthorRequestDTO author) {
        return authorService.updateAuthor(id, author);
    }

    // DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
    }
}
