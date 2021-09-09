package com.saga.example.axon.ticket.query;

import com.saga.example.axon.ticket.event.OrderTicketMovedEvent;
import com.saga.example.axon.ticket.event.OrderTicketPreservedEvent;
import com.saga.example.axon.ticket.event.OrderTicketUnlockedEvent;
import com.saga.example.axon.ticket.event.TicketCreatedEvent;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by mavlarn on 2018/6/26.
 */
@Component
public class TicketProjector {

    private static final Logger LOG = LoggerFactory.getLogger(TicketProjector.class);

    @Autowired
    private TicketEntityRepository ticketEntityRepository;

    @EventSourcingHandler
    public void onCreate(TicketCreatedEvent event) {
        TicketEntity ticket = new TicketEntity();
        ticket.setId(event.getTicketId());
        ticket.setName(event.getName());
        ticketEntityRepository.save(ticket);
        LOG.info("Executed event in TicketProjector:{}", event);
    }

    @EventSourcingHandler
    public void onPreserve(OrderTicketPreservedEvent event) {
//        TicketBuild.builder().
        TicketEntity te = new TicketEntity();
        te.setId(event.getTicketId());
        Optional<TicketEntity> ticket = ticketEntityRepository.findOne(Example.of(te));
        ticket.ifPresent(t->{
            t.setLockUser(event.getCustomerId());
            ticketEntityRepository.save(t);
            LOG.info("Executed event in TicketProjector:{}", event);
        });
    }

    @EventSourcingHandler
    public void onUnlock(OrderTicketUnlockedEvent event) {
        TicketEntity te = new TicketEntity();
        te.setId(event.getTicketId());
        Optional<TicketEntity> ticket = ticketEntityRepository.findOne(Example.of(te));
        ticket.ifPresent(t->{
            t.setLockUser(null);
            ticketEntityRepository.save(t);
            LOG.info("Executed event in TicketProjector:{}", event);

        });
//        TicketEntity ticket = ticketEntityRepository.findOne(event.getTicketId());
//        ticket.setLockUser(null);
//        ticketEntityRepository.save(ticket);
//        LOG.info("Executed event in TicketProjector:{}", event);
    }

    @EventSourcingHandler
    public void onMove(OrderTicketMovedEvent event) {
        TicketEntity te = new TicketEntity();
        te.setId(event.getTicketId());
        Optional<TicketEntity> ticket = ticketEntityRepository.findOne(Example.of(te));
        ticket.ifPresent(t->{
            t.setLockUser(null);
            t.setOwner(event.getCustomerId());
            ticketEntityRepository.save(t);
            LOG.info("Executed event in TicketProjector:{}", event);

        });
//        TicketEntity ticket = ticketEntityRepository.findOne(event.getTicketId());
//        ticket.setLockUser(null);
//        ticket.setOwner(event.getCustomerId());
//        ticketEntityRepository.save(ticket);
//        LOG.info("Executed event in TicketProjector:{}", event);
    }

}
