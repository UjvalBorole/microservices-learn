package com.notification.service.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfo {

    private String orderId;

    private  String userId;

    private  Date createdDate;

    private String price;

    private String emailId;

    private String userPhone;
}
