package com.codeSnippet.CodeSnippet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestOrderDto {

    private String customerName;

    private String productName;
}
