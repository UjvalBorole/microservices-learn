package com.notification.service.functions;

import com.notification.service.dto.OrderInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;
import java.util.function.Supplier;

@Configuration
public class NotificationService {
    //this is the part of spring cloud functions
    //test
    @Bean
    public Supplier<String>testing(){
        return () -> "This is testing";
    }

    @Bean
    public Function<String, String> sayHello(){
        return (message) -> "Hello how are you ?"+ message;
    }

    @Bean
    public Function<OrderInfo,String>orderNotification(){
        return orderInfo -> {
            System.out.println("notification send...");
            //logic to send notification
            sendNotification(orderInfo);
            System.out.println(orderInfo.getEmailId());
            System.out.println(orderInfo.getPrice());
            System.out.println(orderInfo.getUserPhone());
            System.out.println(orderInfo.getCreatedDate());
            return orderInfo.getUserPhone();
        };
    }

    private void sendNotification(OrderInfo orderInfo) {
    }

}
