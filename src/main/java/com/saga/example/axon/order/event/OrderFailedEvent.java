package com.saga.example.axon.order.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by mavlarn on 2018/5/27.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderFailedEvent {

    private String reason;
    private String orderId;

}
