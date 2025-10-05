package com.codeSnippet.CodeSnippet.controller;

import com.codeSnippet.CodeSnippet.dto.AddProductDto;
import com.codeSnippet.CodeSnippet.dto.ProductDto;
import com.codeSnippet.CodeSnippet.entity.Product;
import com.codeSnippet.CodeSnippet.service.CacheInspectionService;
import com.codeSnippet.CodeSnippet.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService productService;
    private final CacheInspectionService cacheService;

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long productId) {
        ResponseEntity<ProductDto> res = ResponseEntity.ok(productService.getProductById(productId));
        System.out.println("Return res " + res);
        return res;
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ProductDto> getProductByName(@PathVariable String name) {
        return ResponseEntity.ok(productService.getProductByName(name));
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProduct() {
        log.info("Get all product of controller called");
        System.out.println("Get all product of controller called");
        return ResponseEntity.ok(productService.getAllProduct());
    }

    //below endPoints for admin
    @PostMapping("/admin")
    public ResponseEntity<Product> addProduct(@Valid @RequestBody AddProductDto addProductDto) {
        return ResponseEntity.ok(productService.addProduct(addProductDto));
    }

    @PutMapping("/admin/updateId/{productId}")
    public ResponseEntity<Product> updateProductById(@PathVariable Long productId, @Valid @RequestBody AddProductDto addProductDto) {
        return ResponseEntity.ok(productService.updateProductById(productId, addProductDto));
    }

    @DeleteMapping("/admin/del/{productId}")
    public ResponseEntity<Boolean> deleteProductById(@PathVariable Long productId) {
        productService.deleteProductById(productId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/admin/deleteAll")
    public ResponseEntity<Boolean> deleteAllProduct() {
        productService.deleteAllProduct();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cacheData")
    public void getCacheData() {
        cacheService.printCacheContent("product");
    }
}
