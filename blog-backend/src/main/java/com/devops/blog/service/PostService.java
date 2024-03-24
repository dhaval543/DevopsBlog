package com.devops.blog.service;

import com.devops.blog.security.service.CustomUserDetails;
import com.devops.blog.common.Exception.BadRequestException;
import com.devops.blog.model.domain.Post;
import com.devops.blog.model.dto.PostDto;
import com.devops.blog.model.domain.User;
import com.devops.blog.repository.PostRepository;
import com.devops.blog.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author Kim Keumtae
 */
@Service
@Transactional
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Optional<Post> findForId(Long id) {
        return postRepository.findById(id);
    }

    public PostDto registerPost(PostDto postDto, CustomUserDetails customUserDetails) {
        Post newPost = new Post();
        newPost.setTitle(postDto.getTitle());
        newPost.setBody(postDto.getBody());
        newPost.setCreatedBy(customUserDetails.getName());
        newPost.setCreatedDate(LocalDateTime.now());
        newPost.setUser(new User(customUserDetails.getId())); // temporary code
        return new PostDto(postRepository.saveAndFlush(newPost));
    }

    public Optional<PostDto> editPost(PostDto editPostDto) {
        return this.findForId(editPostDto.getId())
                .map(p -> {
                    if (p.getUser().getId() != SecurityUtil.getCurrentUserLogin().get().getId()) {
                        throw new BadRequestException("It's not a writer.");
                    }
                    p.setTitle(editPostDto.getTitle());
                    p.setBody(editPostDto.getBody());
                    return p;
                })
                .map(PostDto::new);
    }

    public Page<Post> findByUserOrderedByCreatedDatePageable(User user, Pageable pageable) {
        return postRepository.findByUserOrderByCreatedDateDesc(user, pageable);
    }

    public Page<Post> findAllByOrderByCreatedDateDescPageable(Pageable pageable) {
        return postRepository.findAllByOrderByCreatedDateDesc(pageable);
    }

    public void deletePost(Long id) {
        postRepository.findById(id).ifPresent(p -> {
            if (p.getUser().getId() != SecurityUtil.getCurrentUserLogin().get().getId()) {
                throw new BadRequestException("It's not a writer.");
            }
            postRepository.delete(p);
        });
    }
}
