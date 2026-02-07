package com.library.chapter.application.service;

import com.library.chapter.api.dto.author.AuthorRequestDTO;
import com.library.chapter.api.dto.author.AuthorResponseDTO;
import com.library.chapter.application.mapper.AuthorMapper;
import com.library.chapter.domain.exception.author.AuthorNotFoundException;
import com.library.chapter.domain.exception.author.AuthorEmailAlreadyExistsException;
import com.library.chapter.domain.model.AuthorModel;
import com.library.chapter.infrastructure.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    // toEntity: RequestDTO -> Model toEntity
    // toResponse: Model -> ResponseDTO

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;
    public AuthorService(AuthorRepository authorRepository, AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.authorMapper = authorMapper;
    }

    // CREATE
    @Transactional
    public AuthorResponseDTO createAuthor(AuthorRequestDTO dto) {

        if (authorRepository.existsByEmail(dto.getEmail())) {
            throw new AuthorEmailAlreadyExistsException(dto.getEmail());
        }

        AuthorModel author = authorRepository.save(authorMapper.toEntity(dto));
        return authorMapper.toResponse(author);
    }

    // READ
    @Transactional(readOnly = true)
    public List<AuthorResponseDTO> getAllAuthors() {

        List<AuthorModel> authors = authorRepository.findAll();
        return authors.stream()
                .map(authorMapper::toResponse)
                .collect(Collectors.toList());
    }

    // READ BY ID
    @Transactional(readOnly = true)
    public AuthorResponseDTO getAuthorById(Long id) {

        AuthorModel author = authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException(id));
        return authorMapper.toResponse(author);
    }

    // UPDATE
    @Transactional
    public AuthorResponseDTO updateAuthor(Long id, AuthorRequestDTO dto) {

        AuthorModel existingAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));

        if (!dto.getEmail().equals(existingAuthor.getEmail()) &&
                authorRepository.existsByEmail(dto.getEmail())) {
            throw new AuthorEmailAlreadyExistsException(dto.getEmail());
        }

        authorMapper.updateModelFromDto(dto, existingAuthor);
        return authorMapper.toResponse(authorRepository.save(existingAuthor));
    }

    // DELETE
    @Transactional
    public void deleteAuthor(Long id) {

        if (!authorRepository.existsById(id)) {
            throw new AuthorNotFoundException(id);
        }

        authorRepository.deleteById(id);
    }
}
