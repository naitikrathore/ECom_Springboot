package com.codeSnippet.CodeSnippet.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {
    private int status;
    private String message;
    private LocalDateTime timeStamp;

    public ErrorResponse(int status, String message, LocalDateTime timeStamp){
        this.status = status;
        this.message = message;
        this.timeStamp = timeStamp;
    }
}
