package com.shubham.order_service.web.controller;

import com.shubham.core.dto.Order;
import com.shubham.order_service.dto.CreatedOrderResponse;
import com.shubham.order_service.dto.OrderHistoryReponse;
import com.shubham.order_service.service.OrderHistoryService;
import com.shubham.order_service.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderHistoryService orderHistoryService;

    public OrderController(OrderService orderService, OrderHistoryService orderHistoryService){
        this.orderService=orderService;
        this.orderHistoryService=orderHistoryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CreatedOrderResponse placeOrder(@RequestBody @Valid CreatedOrderResponse request){
        var order = new Order();
        BeanUtils.copyProperties(request,order);
        Order createdOrder = orderService.placeOrder(order);

        var respose = new CreatedOrderResponse();
        BeanUtils.copyProperties(createdOrder,respose);
        return respose;

    }

    @GetMapping("/{orderId}/history")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderHistoryReponse> getOrderHistory(@PathVariable UUID orderId){
        return orderHistoryService.findByOrderId(orderId).stream().map(orderHistory ->
        { OrderHistoryReponse orderHistoryReponse = new OrderHistoryReponse();
                BeanUtils.copyProperties(orderHistory,orderHistoryReponse);
                return orderHistoryReponse;
    }).toList();

    }


}
