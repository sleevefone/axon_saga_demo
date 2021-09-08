package com.imooc.example.axon.order.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by mavlarn on 2018/5/27.
 */

@Data@NoArgsConstructor
@AllArgsConstructor
public class OrderFinishedEvent {

    private String orderId;


}
