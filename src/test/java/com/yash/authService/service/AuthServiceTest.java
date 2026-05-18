package com.yash.authService.service;

import com.yash.authService.dto.ApiResponse;
import com.yash.authService.dto.AuthResponse;
import com.yash.authService.dto.LoginRequest;
import com.yash.authService.dto.SignupRequest;
import com.yash.authService.enums.Role;
import com.yash.authService.entity.User;
import com.yash.authService.exception.EmailAlreadyExistsException;
import com.yash.authService.repository.UserRepository;
import com.yash.authService.config.JwtService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthService authService;

    @Test
    void signupSuccessTest() {
        SignupRequest request = new SignupRequest();
        request.setEmail("yash@gmail.com");
        request.setPassword("yash123");
        request.setUserName("yash Uzumaki");

        when(userRepository.existsByEmail("yash@gmail.com")).thenReturn(false);
        when(passwordEncoder.encode("yash123")).thenReturn("encodedPassword");

        ApiResponse<?> response = authService.signup(request);

        assertEquals("User registered successfully", response.getMessage());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void signupFailureTest() {
        SignupRequest request = new SignupRequest();
        request.setEmail("yash@gmail.com");
        request.setPassword("yash123");
        request.setUserName("yash Uzumaki");

        when(userRepository.existsByEmail("yash@gmail.com")).thenReturn(true);

        assertThrows(
                EmailAlreadyExistsException.class,
                () -> authService.signup(request)
        );
        verify(userRepository, never()).save(any());
    }

    @Test
    void loginSuccessTest() {
        LoginRequest request = new LoginRequest();
        request.setEmail("yash@gmail.com");
        request.setPassword("yash123");

        User user = new User();
        user.setId(1L);
        user.setEmail("yash@gmail.com");
        user.setPassword("yash123");
        user.setUserName("Yash Uzumaki");
        user.setRole(Role.ROLE_USER);

        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(user);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);
        when(jwtService.generateToken(user)).thenReturn("generated-jwt-token");

        ApiResponse<AuthResponse> response = authService.login(request);

        assertEquals("Login successful", response.getMessage());
        assertNotNull(response.getData());
        assertEquals("generated-jwt-token", response.getData().getToken());
        assertEquals("yash@gmail.com", response.getData().getEmail());
        assertEquals("ROLE_USER", response.getData().getRole());
    }

    @Test
    void loginFailureTest() {
        LoginRequest request = new LoginRequest();
        request.setEmail("yash@gmail.com");
        request.setPassword("yash123");

        when(authenticationManager.authenticate(any()))
                .thenThrow(new BadCredentialsException("Invalid credentials"));

        assertThrows(BadCredentialsException.class, () -> authService.login(request));
    }
}