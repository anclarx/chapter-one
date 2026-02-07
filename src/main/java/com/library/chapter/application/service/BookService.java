package com.library.chapter.application.service;

import com.library.chapter.api.dto.book.BookRequestDTO;
import com.library.chapter.api.dto.book.BookResponseDTO;
import com.library.chapter.application.mapper.BookMapper;
import com.library.chapter.domain.exception.author.AuthorNotFoundException;
import com.library.chapter.domain.exception.author.AuthorsNotFoundException;
import com.library.chapter.domain.exception.book.*;
import com.library.chapter.domain.model.AuthorModel;
import com.library.chapter.domain.model.BookModel;
import com.library.chapter.infrastructure.repository.AuthorRepository;
import com.library.chapter.infrastructure.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class BookService {

    // toEntity: RequestDTO -> Model toEntity
    // toResponse: Model -> ResponseDTO

    private static final Pattern ISBN_PATTERN = Pattern.compile(
            "^(?:ISBN(?:-13)?:? )?(?=[0-9]{13}$|(?=(?:[0-9]+[- ]){4})[- 0-9]{17}$)97[89][- ]?[0-9]{1,5}[- ]?[0-9]+[- ]?[0-9]+[- ]?[0-9]$"
    );

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

        if (bookRepository.existsByIsbn(dto.getIsbn())) {
            throw new BookIsbnAlreadyExistsException(dto.getIsbn());
        }

        List<AuthorModel> authors = authorRepository.findAllById(dto.getAuthorIds());
        validateAuthors(dto.getAuthorIds(), authors);

        BookModel book = bookMapper.toEntity(dto);
        book.setAuthors(authors);

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

        if (!dto.getIsbn().equals(existingBook.getIsbn()) &&
                bookRepository.existsByIsbn(dto.getIsbn())) {
            throw new BookIsbnAlreadyExistsException(dto.getIsbn());
        }

        List<AuthorModel> authors = authorRepository.findAllById(dto.getAuthorIds());
        validateAuthors(dto.getAuthorIds(), authors);

        bookMapper.updateModelFromDto(dto, existingBook);
        existingBook.setAuthors(authors);

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

    // VALIDATION
    private void validateAuthors(List<Long> requestedIds, List<AuthorModel> foundAuthors) {
        if (foundAuthors.size() != requestedIds.size()) {
            List<Long> foundIds = foundAuthors.stream().map(AuthorModel::getId).toList();
            List<Long> missingIds = requestedIds.stream()
                    .filter(id -> !foundIds.contains(id)).toList();

            if (missingIds.size() > 1) {
                throw new AuthorsNotFoundException(missingIds);
            } else {
                throw new AuthorNotFoundException(missingIds.getFirst());
            }
        }
    }
}
