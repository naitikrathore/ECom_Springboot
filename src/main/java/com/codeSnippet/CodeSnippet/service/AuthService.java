package com.codeSnippet.CodeSnippet.service;

import com.codeSnippet.CodeSnippet.dto.LoginDto;
import com.codeSnippet.CodeSnippet.entity.Users;
import com.codeSnippet.CodeSnippet.util.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JWTUtils jwtUtils;

    public String verifyLogin(LoginDto loginDto) {
        Authentication authenticated = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
        );

        // At this point, authenticationManager.authenticate(...) has delegated to
        // DaoAuthenticationProvider, which in turn called our CustomUserDetailsService.
        // Our CustomUserDetailsService returned a Users object (our custom class implementing UserDetails).
        // Spring Security then put that same Users object into the Authentication's 'principal' field.
        // So when we call authenticated.getPrincipal(), we are simply retrieving the exact
        // Users object we returned earlier — not magic, just the same instance passed back.
        Users user = (Users) authenticated.getPrincipal();

        return jwtUtils.generateToken(user.getUsername(), user.getPassword());
    }
}
