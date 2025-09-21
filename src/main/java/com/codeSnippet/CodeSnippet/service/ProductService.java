package com.codeSnippet.CodeSnippet.service;

import com.codeSnippet.CodeSnippet.dto.AddProductDto;
import com.codeSnippet.CodeSnippet.dto.ProductDto;
import com.codeSnippet.CodeSnippet.entity.Product;
import com.codeSnippet.CodeSnippet.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
//@Profile("dev")
//@Transactional  this helps to follow Atomicity property it can be class level or method level and Transitional won't apply for private method
public class ProductService {

    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;


    /**
     * If i call for id 1 then first time it will execute to store the res inMemory and from next time
     * it will not enter the below function only, it will check in cache nd return the result from there only.
     */

//    Here #productId means “use the method parameter named productId as the cache key.”
//    @Cacheable(value = "products", key = "#productId")
    public ProductDto getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("No Product with this id"));

        return modelMapper.map(product,ProductDto.class);
    }

    public ProductDto getProductByName(String name) {
        Product product = productRepository.findByName(name).orElseThrow(() -> new IllegalStateException("Product Not exist with name: "+name));
        System.out.println("Product by name : " + product);
        return modelMapper.map(product,ProductDto.class);
    }

    public List<ProductDto> getAllProduct() {
        System.out.println("Get all product of service called");
        List<Product> productList = productRepository.findAll();

        return productList
                .stream()
                .map(product -> modelMapper.map(product,ProductDto.class))
                .toList();
    }


    @Transactional
    public Product addProduct(AddProductDto addProductDto) {
        Product product = modelMapper.map(addProductDto,Product.class);
        return productRepository.save(product);
    }

    //to avoid stale data getting from cache now it will first update the DB then it will update the cache
//    @CachePut(value = "product", key = "#productId")
    public Product updateProductById(Long productId, AddProductDto addProductDto) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("No Product with this id"));
        modelMapper.map(addProductDto,product);
        return productRepository.save(product);
    }

    public void deleteProductById(Long productId) {
        if(!productRepository.existsById(productId)){
            throw new IllegalStateException("Product Not exist with id: "+productId);
        }
        productRepository.deleteById(productId);
    }

//    @CacheEvict(value = "product", key = "#productId")
    public void deleteAllProduct() {
        productRepository.deleteAll();
    }

    @PostConstruct
    public void init(){
        System.out.println("After bean created");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("Before bean destroy");
    }
}
