package com.codeSnippet.CodeSnippet.controller;

import com.codeSnippet.CodeSnippet.dto.RequestOrderDto;
import com.codeSnippet.CodeSnippet.dto.ResponseOrderDto;
import com.codeSnippet.CodeSnippet.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<ResponseOrderDto>> getAllOrders() {
        log.info("get all called order");
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<ResponseOrderDto> getOrderByOrderId(@PathVariable Long orderId){
        return ResponseEntity.ok(orderService.getOrderByOrderId(orderId));
    }

    @PostMapping
    public  ResponseEntity<ResponseOrderDto> saveOrder(@RequestBody @Valid RequestOrderDto requestOrderDto){
        return ResponseEntity.ok(orderService.saveOrder(requestOrderDto));
    }

}
