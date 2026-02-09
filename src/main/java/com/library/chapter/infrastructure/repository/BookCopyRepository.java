package com.library.chapter.infrastructure.repository;

import com.library.chapter.domain.model.BookCopyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookCopyRepository extends JpaRepository<BookCopyModel, Long> {
}
