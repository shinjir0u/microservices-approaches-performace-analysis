package com.choreography.order.controller;

import com.choreography.order.model.dto.OrderRequest;
import com.choreography.order.usecase.CreateOrderUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private CreateOrderUseCase createOrderUseCase;

    @PostMapping("/request")
    public void createOrder(@RequestBody OrderRequest request) {
        createOrderUseCase.execute(request);
    }

}
