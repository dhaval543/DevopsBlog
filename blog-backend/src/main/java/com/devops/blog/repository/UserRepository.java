package com.devops.blog.repository;

import com.devops.blog.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneById(Long id);
    Optional<User> findByEmail(String email);
    Optional<User> findOneWithAuthoritiesByEmail(String lowercaseEmail);

    Boolean existsByEmail(String email);
}