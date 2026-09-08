package com.orchestration.order.controller;

import com.orchestration.order.model.dto.order.OrderRequest;
import com.orchestration.order.usecase.CreateOrderUsecase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderController {

    private final CreateOrderUsecase createOrderUsecase;

    @PostMapping("/orders/request")
    public void requestOrder(@RequestBody OrderRequest orderRequest) {
        createOrderUsecase.execute(orderRequest);
    }

}
