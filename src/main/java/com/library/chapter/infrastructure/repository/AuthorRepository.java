package com.library.chapter.infrastructure.repository;

import com.library.chapter.domain.model.AuthorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorModel, Long> {

    List<AuthorModel> findByNameContaining(String name);
    boolean existsByEmail(String email);
}
