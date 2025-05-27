package com.oder.service.order_service.service;

import com.oder.service.order_service.OrderDetail;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
public class OrderService {
    public OrderDetail createOrder(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId(UUID.randomUUID().toString());
        orderDetail.setEmail("manish@gmail.com");
        orderDetail.setUserPhone("1234567890");
        orderDetail.setUserId("345678");
        orderDetail.setCourseId(UUID.randomUUID().toString());
        //save this to database
        //mysql-jpa,mongo-db -> you will do this process or other process
        return orderDetail;
    }
}
