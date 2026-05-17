package com.yash.authService.controller;

import com.yash.authService.dto.ApiResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/protected")
public class ProtectedController {

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ApiResponse<String> userEndpoint() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return new ApiResponse<>("Success", "Hello " + auth.getName() + " you have USER access", null);
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ApiResponse<String> adminEndpoint() {
        return new ApiResponse<>("Success", "Admin access granted", null);
    }
}
