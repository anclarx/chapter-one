package com.library.chapter.application.service;

import com.library.chapter.api.dto.book.BookRequestDTO;
import com.library.chapter.api.dto.book.BookResponseDTO;
import com.library.chapter.application.mapper.BookMapper;
import com.library.chapter.domain.exception.AuthorNotFoundException;
import com.library.chapter.domain.exception.BookNotFoundException;
import com.library.chapter.domain.model.AuthorModel;
import com.library.chapter.domain.model.BookModel;
import com.library.chapter.infrastructure.repository.AuthorRepository;
import com.library.chapter.infrastructure.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    // toEntity: RequestDTO -> Model toEntity
    // toResponse: Model -> ResponseDTO

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final AuthorRepository authorRepository;
    public BookService(BookRepository bookRepository, BookMapper bookMapper, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.authorRepository = authorRepository;
    }

    // CREATE
    @Transactional
    public BookResponseDTO createBook(BookRequestDTO dto) {

        AuthorModel author = authorRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new AuthorNotFoundException(dto.getAuthorId()));

        BookModel book = bookMapper.toEntity(dto);
        book.setAuthor(author);

        return bookMapper.toResponse(bookRepository.save(book));
    }

    // READ
    @Transactional(readOnly = true)
    public List<BookResponseDTO> getAllBooks() {

        List<BookModel> books = bookRepository.findAll();
        return books.stream()
                .map(bookMapper::toResponse)
                .collect(Collectors.toList());
    }

    // READ BY ID
    @Transactional(readOnly = true)
    public BookResponseDTO getBookById(Long id) {

        BookModel book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        return bookMapper.toResponse(book);
    }

    // UPDATE
    @Transactional
    public BookResponseDTO updateBook(Long id, BookRequestDTO dto) {

        BookModel existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        if (!dto.getAuthorId().equals(existingBook.getAuthor().getId())) {

            AuthorModel newAuthor = authorRepository.findById(dto.getAuthorId())
                    .orElseThrow(() -> new AuthorNotFoundException(dto.getAuthorId()));

            existingBook.setAuthor(newAuthor);
        }

        bookMapper.updateModelFromDto(dto, existingBook);
        return bookMapper.toResponse(bookRepository.save(existingBook));
    }

    // DELETE
    @Transactional
    public void deleteBook(Long id) {

        if (!bookRepository.existsById(id)) {
            throw new AuthorNotFoundException(id);
        }

        bookRepository.deleteById(id);
    }
}
