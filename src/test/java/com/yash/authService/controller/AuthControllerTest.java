package com.yash.authService.controller;

import com.yash.authService.dto.ApiResponse;
import com.yash.authService.dto.AuthResponse;
import com.yash.authService.dto.Loginrequest;
import com.yash.authService.dto.SignupRequest;
import com.yash.authService.service.AuthService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    @Test
    void signupTest() {
        SignupRequest request = new SignupRequest();
        request.setEmail("yash@gmail.com");
        request.setPassword("yash123");
        request.setUserName("Yash Uzumaki");
        ApiResponse<?> mockResponse = new ApiResponse<>("Success", "User registered successfully", null);

        doReturn(mockResponse).when(authService).signup(request);

        ResponseEntity<ApiResponse<?>> result = authController.signup(request);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertNotNull(result.getBody());
        assertEquals(mockResponse.getMessage(), result.getBody().getMessage());
    }

    @Test
    void loginTest() {
        Loginrequest request = new Loginrequest("yash@gmail.com", "yash123");
        AuthResponse authResponse = new AuthResponse("Yash uzumaki", "yash@gmail.com", "ROLE_USER", "jwt-token-123");
        ApiResponse<AuthResponse> mockResponse = new ApiResponse<>("Success", "Login successful", authResponse);

        doReturn(mockResponse).when(authService).login(any(Loginrequest.class));

        ResponseEntity<ApiResponse<AuthResponse>> result = authController.login(request);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertNotNull(result.getBody());
        assertEquals("Success", result.getBody().getResult());
    }
}