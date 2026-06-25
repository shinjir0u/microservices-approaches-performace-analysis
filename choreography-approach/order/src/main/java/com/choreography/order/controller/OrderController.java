package com.choreography.order.controller;

import com.choreography.order.model.dto.OrderRequest;
import com.choreography.order.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/request")
    public void createOrder(@RequestBody OrderRequest request) {
        orderService.createOrder(request);
    }

}
