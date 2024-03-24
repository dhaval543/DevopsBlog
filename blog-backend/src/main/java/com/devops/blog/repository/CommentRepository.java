package com.devops.blog.repository;

import com.devops.blog.model.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Kim Keumtae
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    public Optional<List<Comment>> findByPostId(Long postId);

}
