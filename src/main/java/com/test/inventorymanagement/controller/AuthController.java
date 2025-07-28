package com.test.inventorymanagement.controller;

import com.test.inventorymanagement.dto.AuthRequest;
import com.test.inventorymanagement.dto.AuthResponse;
import com.test.inventorymanagement.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Tag(name = "1. Authentication", description = "APIs for User Registration and Login")
public class AuthController {

  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @Operation(
      summary = "Register a new user",
      description = "Creates a new user account. The username must be unique.",
      responses = {
          @ApiResponse(responseCode = "201", description = "User registered successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Map.class))),
          @ApiResponse(responseCode = "409", description = "Conflict - User already exists", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Map.class))),
          @ApiResponse(responseCode = "400", description = "Bad Request - Invalid input data")
      }
  )
  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@Valid @RequestBody AuthRequest registerRequest) {
    authService.register(registerRequest);
    return new ResponseEntity<>(Map.of("message", "User registered successfully"), HttpStatus.CREATED);
  }

  @Operation(
      summary = "Authenticate a user",
      description = "Logs in a user with username and password, returning a JWT access token upon success.",
      responses = {
          @ApiResponse(responseCode = "200", description = "Login successful", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthResponse.class))),
          @ApiResponse(responseCode = "401", description = "Unauthorized - Invalid credentials"),
          @ApiResponse(responseCode = "400", description = "Bad Request - Invalid input data")
      }
  )
  @PostMapping("/login")
  public ResponseEntity<AuthResponse> loginUser(@Valid @RequestBody AuthRequest authRequest) {
    String token = authService.login(authRequest);
    return ResponseEntity.ok(new AuthResponse(token));
  }
}
