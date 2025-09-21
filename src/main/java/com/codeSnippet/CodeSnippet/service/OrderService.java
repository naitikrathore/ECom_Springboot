package com.codeSnippet.CodeSnippet.service;

import com.codeSnippet.CodeSnippet.dto.RequestOrderDto;
import com.codeSnippet.CodeSnippet.dto.ResponseOrderDto;
import com.codeSnippet.CodeSnippet.entity.Order;
import com.codeSnippet.CodeSnippet.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    public List<ResponseOrderDto> getAllOrders() {
        List<Order> list = orderRepository.findAll();
        return list
                .stream()
                .map(order -> modelMapper.map(order,ResponseOrderDto.class))
                .toList();
    }

    public ResponseOrderDto getOrderByOrderId(Long orderId) {
        Order order = orderRepository.findByOrderId(orderId).orElseThrow(() -> new IllegalStateException("No Order found with order id :"+orderId));
        return modelMapper.map(order,ResponseOrderDto.class);
    }

    public ResponseOrderDto saveOrder(RequestOrderDto requestOrderDto) {
        Order toSave = modelMapper.map(requestOrderDto,Order.class);
        toSave.setOrderId(generateUniqueOrderId());
        Order order = orderRepository.save(toSave);
        return modelMapper.map(order,ResponseOrderDto.class);
    }

    private Long generateUniqueOrderId() {
        Long id;
        do {
            id = (long) (1000 + new Random().nextInt(9000)); // 1000-9999
        } while (orderRepository.existsByOrderId(id));
        return id;
    }

    /**
     * Demonstrates difference between fixedRate and fixedDelay scheduling.
     *
     * fixedRate:
     * - Runs the task every X milliseconds, measured from the start of the previous run.
     * - Does not wait for the task to finish. If the task takes longer than X, runs may overlap.
     *
     * fixedDelay:
     * - Runs the task with a delay of X milliseconds after the previous run finishes.
     * - Always waits for completion before starting the delay countdown.
     */
//    @Scheduled(fixedRate = 5000)
    private void completePendingOrders(){
        List<Order> pendingList = orderRepository.findByOrderStatus("PENDING");
        System.out.println("Pending Orders size " + pendingList.size());
        if(!pendingList.isEmpty()){
            pendingList.forEach(order -> {
                order.setOrderStatus("COMPLETED");
                orderRepository.save(order);
            });
        }else{
            System.out.println("No Pending order found");
        }
    }

    @Scheduled(cron = "0 0 1 * * ?")
    public void nightlyJob() {
        System.out.println("Night cleanUp");
    }
}
