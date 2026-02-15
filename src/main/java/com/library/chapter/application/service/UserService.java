package com.library.chapter.application.service;

import com.library.chapter.api.dto.user.UserRequestDTO;
import com.library.chapter.api.dto.user.UserResponseDTO;
import com.library.chapter.application.mapper.UserMapper;
import com.library.chapter.domain.exception.user.UserEmailAlreadyExistsException;
import com.library.chapter.domain.exception.user.UserLoginAlreadyExistsException;
import com.library.chapter.domain.exception.user.UserNotFoundException;
import com.library.chapter.domain.model.UserModel;
import com.library.chapter.infrastructure.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    // toEntity: RequestDTO -> Model toEntity
    // toResponse: Model -> ResponseDTO

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    // CREATE
    @Transactional
    public UserResponseDTO createUser(UserRequestDTO dto) {

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new UserEmailAlreadyExistsException(dto.getEmail());
        }

        if (userRepository.existsByLogin(dto.getLogin())) {
            throw new UserLoginAlreadyExistsException(dto.getLogin());
        }

        UserModel user = userRepository.save(userMapper.toEntity(dto));
        return userMapper.toResponse(user);
    }

    // READ
    @Transactional(readOnly = true)
    public List<UserResponseDTO> getAllUsers() {

        List<UserModel> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
    }

    // READ BY ID
    @Transactional(readOnly = true)
    public UserResponseDTO getUserById(Long id) {

        UserModel user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return userMapper.toResponse(user);
    }

    // UPDATE
    @Transactional()
    public UserResponseDTO updateUser(Long id, UserRequestDTO dto) {

        UserModel existingUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));

        if (!dto.getEmail().equals(existingUser.getEmail()) &&
                userRepository.existsByEmail(dto.getEmail())) {
            throw new UserEmailAlreadyExistsException(dto.getEmail());
        }

        if (!dto.getLogin().equals(existingUser.getLogin()) &&
                userRepository.existsByLogin(dto.getLogin())) {
            throw new UserLoginAlreadyExistsException(dto.getLogin());
        }

        userMapper.updateModelFromDto(dto, existingUser);
        return userMapper.toResponse(userRepository.save(existingUser));
    }

    // DELETE
    @Transactional()
    public void deleteUser(Long id) {

        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }

        userRepository.deleteById(id);
    }
}
