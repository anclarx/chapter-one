package com.library.chapter.infrastructure.repository;

import com.library.chapter.domain.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    boolean existsByEmail(String email);
    boolean existsByLogin(String login);
    UserDetails findByLogin(String login);
}
