package com.codeSnippet.CodeSnippet.controller;

import com.codeSnippet.CodeSnippet.dto.LoginDto;
import com.codeSnippet.CodeSnippet.dto.RegisteredUserDto;
import com.codeSnippet.CodeSnippet.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
        return ResponseEntity.ok(authService.verifyLogin(loginDto));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisteredUserDto> registerUser(@RequestBody RegisteredUserDto registeredUserDto){
        return ResponseEntity.ok(authService.registerUser(registeredUserDto));
    }
}
