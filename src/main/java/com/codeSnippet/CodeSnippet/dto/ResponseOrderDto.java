package com.codeSnippet.CodeSnippet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseOrderDto {

    private Long orderId;

    private String customerName;

    private LocalDateTime orderDate = LocalDateTime.now();

    private String productName;

    private String orderStatus;
}
