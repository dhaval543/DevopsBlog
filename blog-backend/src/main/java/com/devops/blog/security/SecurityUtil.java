package com.devops.blog.security;

import com.devops.blog.security.service.CustomUserDetails;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * @author Kim Keumtae
 */
public class SecurityUtil {

    public static Optional<CustomUserDetails> getCurrentUserLogin() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(securityContext.getAuthentication())
                .map(authentication -> {
                    if (authentication.getPrincipal() instanceof CustomUserDetails) {
                        return (CustomUserDetails) authentication.getPrincipal();
                    }
                    return null;
                });
    }
}
