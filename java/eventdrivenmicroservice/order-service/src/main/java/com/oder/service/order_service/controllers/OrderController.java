package com.oder.service.order_service.controllers;

import com.oder.service.order_service.OrderDetail;
import com.oder.service.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }
//    event will send to broker and broker consumes notification service
    @PostMapping
    public ResponseEntity<?>createOrder(){
        //order create logic
        OrderDetail order = this.orderService.createOrder();

        //send notification to notification service:so that notifiaction service send the email ans message to user
        orderCreatedNotification(order);
        return ResponseEntity.ok("Order Created");
    }

    @Autowired
    private StreamBridge streamBridge;

    private void orderCreatedNotification(OrderDetail order) {
//        logic to send notification to notification service
        boolean send = streamBridge.send("orderCreatedEvent-out-0",order);
        if(send){
            System.out.println("Order Success Event is successfully send to notification service");
        }else{
            System.out.println("Event Fails:");
        }
    }
}
