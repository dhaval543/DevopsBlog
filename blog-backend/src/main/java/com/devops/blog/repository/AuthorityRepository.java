package com.devops.blog.repository;

import com.devops.blog.model.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Kim Keumtae
 */
@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}