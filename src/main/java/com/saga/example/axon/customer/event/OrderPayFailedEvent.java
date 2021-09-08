package com.saga.example.axon.customer.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by mavlarn on 2018/5/27.
 */
@Data@NoArgsConstructor
@AllArgsConstructor
public class OrderPayFailedEvent {

    private String orderId;



    @Override
    public String toString() {
        return "OrderPayFailedEvent{" +
                "orderId='" + orderId + '\'' +
                '}';
    }
}
