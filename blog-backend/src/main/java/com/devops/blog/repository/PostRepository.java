package com.devops.blog.repository;

import com.devops.blog.model.domain.Post;
import com.devops.blog.model.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByUserOrderByCreatedDateDesc(User user, Pageable pageable);

    Page<Post> findAllByOrderByCreatedDateDesc(Pageable pageable);

    Optional<Post> findById(Long id);

    void delete(Post post);
}