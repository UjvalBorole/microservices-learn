package com.oder.service.order_service.function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class NotifyService {

    @Bean
    public Consumer<String> consumeAck(){
        return (str)->{
            updateOrder();
          System.out.println("Receved Ack" +str);
        };
    }

    private void updateOrder() {
    }
}
