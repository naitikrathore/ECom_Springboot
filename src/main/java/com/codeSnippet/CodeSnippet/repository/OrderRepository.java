package com.codeSnippet.CodeSnippet.repository;

import com.codeSnippet.CodeSnippet.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    Optional<Order> findByOrderId(Long orderId);

    Boolean existsByOrderId(Long orderId);

    List<Order> findByOrderStatus(String orderStatus);
}
