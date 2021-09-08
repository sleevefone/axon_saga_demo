package com.imooc.example.axon.ticket.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by mavlarn on 2018/5/27.
 */
@Data@NoArgsConstructor
@AllArgsConstructor
public class OrderTicketUnlockedEvent {

    private String ticketId;

}
