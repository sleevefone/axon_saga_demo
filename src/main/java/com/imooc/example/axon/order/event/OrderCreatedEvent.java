package com.imooc.example.axon.order.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

/**
 * Created by mavlarn on 2018/5/24.
 */
@Data@NoArgsConstructor
@AllArgsConstructor
public class OrderCreatedEvent {

    private String orderId;

    private String customerId;

    private String title;

    private String ticketId;

    private Double amount;

    private ZonedDateTime createdDate;


}
