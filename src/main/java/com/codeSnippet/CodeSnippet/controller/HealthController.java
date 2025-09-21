package com.codeSnippet.CodeSnippet.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/health_check")
    public String healthCheck(){
        return "Ok";
    }
}