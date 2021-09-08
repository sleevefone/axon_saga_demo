package com.saga.example.axon.ticket.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by mavlarn on 2018/5/28.
 */
@Data@NoArgsConstructor
@AllArgsConstructor
public class TicketCreatedEvent {

    private String ticketId;

    private String name;


}
