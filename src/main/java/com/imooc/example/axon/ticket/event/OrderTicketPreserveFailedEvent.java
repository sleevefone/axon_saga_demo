package com.imooc.example.axon.ticket.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by mavlarn on 2018/5/26.
 */
@Data@NoArgsConstructor
@AllArgsConstructor
public class OrderTicketPreserveFailedEvent {

    private String orderId;

}
