package com.codeSnippet.CodeSnippet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddProductDto {

    private String name;

    private Double price;

    private String description;

    private Long stock;

    private Double costPrice;
}
