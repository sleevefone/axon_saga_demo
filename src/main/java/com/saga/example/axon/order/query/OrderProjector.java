package com.saga.example.axon.order.query;

import com.saga.example.axon.order.event.OrderCreatedEvent;
import com.saga.example.axon.order.event.OrderFailedEvent;
import com.saga.example.axon.order.event.OrderFinishedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by mavlarn on 2018/5/22.
 */
@Service
public class OrderProjector {

    @Autowired
    private OrderEntityRepository repository;

    @EventHandler
    public void on(OrderCreatedEvent event) {
        OrderEntity order = new OrderEntity();
        order.setId(event.getOrderId());
        order.setCustomerId(event.getCustomerId());
        order.setTicketId(event.getTicketId());
        order.setAmount(event.getAmount());
        order.setCreatedDate(event.getCreatedDate());
        order.setTitle(event.getTitle());
        order.setStatus("NEW");
        repository.save(order);
    }

    @EventHandler
    public void on(OrderFinishedEvent event) {
        OrderEntity oe = new OrderEntity();
        oe.setId(event.getOrderId());
        Optional<OrderEntity> order = repository.findOne(Example.of(oe));
        order.ifPresent(o->{
            o.setStatus("FINISH");
            repository.save(o);

        });
//        OrderEntity order = repository.findOne(event.getOrderId());
//        order.setStatus("FINISH");
//        repository.save(order);
    }

    @EventHandler
    public void on(OrderFailedEvent event) {
        OrderEntity oe = new OrderEntity();
        oe.setId(event.getOrderId());
        Optional<OrderEntity> order = repository.findOne(Example.of(oe));
        order.ifPresent(o->{
            o.setStatus("FAILED");
            o.setReason(event.getReason());
            repository.save(o);

        });

    }
}
