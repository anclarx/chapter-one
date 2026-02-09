package com.library.chapter.application.service;

import com.library.chapter.api.dto.copy.BookCopyResponseDTO;
import com.library.chapter.api.dto.copy.BookCopyRequestDTO;
import com.library.chapter.application.mapper.BookCopyMapper;
import com.library.chapter.domain.exception.book.BookNotFoundException;
import com.library.chapter.domain.exception.copy.BookCopyNotFoundException;
import com.library.chapter.domain.model.BookCopyModel;
import com.library.chapter.domain.model.BookModel;
import com.library.chapter.infrastructure.repository.BookCopyRepository;
import com.library.chapter.infrastructure.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookCopyService {

    // toEntity: RequestDTO -> Model toEntity
    // toResponse: Model -> ResponseDTO

    private final BookCopyRepository bookCopyRepository;
    private final BookCopyMapper bookCopyMapper;
    private final BookRepository bookRepository;
    public BookCopyService(BookCopyRepository bookCopyRepository, BookCopyMapper bookCopyMapper, BookRepository bookRepository) {
        this.bookCopyRepository = bookCopyRepository;
        this.bookCopyMapper = bookCopyMapper;
        this.bookRepository = bookRepository;
    }

    // CREATE
    @Transactional
    public BookCopyResponseDTO createBookCopy(BookCopyRequestDTO dto) {

        BookModel book = bookRepository.findById(dto.getBookModelId())
                .orElseThrow(() -> new BookNotFoundException(dto.getBookModelId()));

        BookCopyModel bookCopy = bookCopyMapper.toEntity(dto);
        bookCopy.setBookModel(book);

        return bookCopyMapper.toResponse(bookCopyRepository.save(bookCopy));
    }

    // READ
    @Transactional(readOnly = true)
    public List<BookCopyResponseDTO> getAllBookCopies () {

        List<BookCopyModel> bookCopies = bookCopyRepository.findAll();
        return bookCopies.stream()
                .map(bookCopyMapper::toResponse)
                .collect(Collectors.toList());
    }

    // READ BY ID
    @Transactional(readOnly = true)
    public BookCopyResponseDTO getBookCopyById(Long id) {

        BookCopyModel bookCopy = bookCopyRepository.findById(id).orElseThrow(() -> new BookNotFoundException(id));
        return bookCopyMapper.toResponse(bookCopy);
    }

    // UPDATE
    @Transactional
    public BookCopyResponseDTO updateBookCopy(Long id, BookCopyRequestDTO dto) {

        BookCopyModel existingBookCopy = bookCopyRepository.findById(id)
                .orElseThrow(() -> new BookCopyNotFoundException(id));

        BookModel bookModel = bookRepository.findById(dto.getBookModelId())
                .orElseThrow(() -> new BookNotFoundException(dto.getBookModelId()));

        bookCopyMapper.updateModelFromDto(dto, existingBookCopy);
        existingBookCopy.setBookModel(bookModel);

        return bookCopyMapper.toResponse(bookCopyRepository.save(existingBookCopy));
    }

    // DELETE
    @Transactional
    public void deleteBookCopy(Long id) {

        if (!bookCopyRepository.existsById(id)) {
            throw new BookCopyNotFoundException(id);
        }

        bookCopyRepository.deleteById(id);
    }
}
