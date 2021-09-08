package com.saga.example.axon;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.saga.example.axon.customer.command.OrderPayCommand;
import com.saga.example.axon.customer.event.OrderPayFailedEvent;
import com.saga.example.axon.order.OrderManagementSaga;
import com.saga.example.axon.order.command.OrderFinishCommand;
import com.saga.example.axon.order.event.OrderCreatedEvent;
import com.saga.example.axon.ticket.Ticket;
import com.saga.example.axon.ticket.command.OrderTicketMoveCommand;
import com.saga.example.axon.ticket.command.OrderTicketPreserveCommand;
import com.saga.example.axon.ticket.command.OrderTicketUnlockCommand;
import com.saga.example.axon.ticket.event.OrderTicketMovedEvent;
import com.saga.example.axon.ticket.event.OrderTicketPreservedEvent;
import com.saga.example.axon.ticket.event.OrderTicketUnlockedEvent;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.axonframework.test.aggregate.ResultValidator;
import org.axonframework.test.saga.SagaTestFixture;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

/**
 * @author fanhb on 2021/9/8
 * {@link org.axonframework.test.aggregate.FixtureConfiguration}
 * doc: https://docs.axoniq.io/reference-guide/axon-framework/testing/sagas-1
 */
public class MyExample {

    private SagaTestFixture<OrderManagementSaga> fixture;

    @Before
    public void setUp() {
        fixture = new SagaTestFixture<>(OrderManagementSaga.class);

//        OrderTicketMoveCommand commandHandler = new OrderTicketMoveCommand("tId1","oId1","cId1");
//        OrderTicketMoveCommand commandHandler = new OrderTicketMovedEvent("tId1","oId1","cId1");
//        commandHandler.setRepository(fixture.getRepository());
//        fixture.registerAnnotatedCommandHandler(commandHandler);
    }

    /**
     * <pre>
     * FixtureConfiguration<InvoicingSaga> fixture = new SagaTestFixture<>(InvoicingSaga.class);
     * fixture.givenAggregate(invoiceId).published(new InvoiceCreatedEvent())
     *        .whenTimeElapses(Duration.ofDays(31))
     *        .expectDispatchedCommandsMatching(Matchers.listWithAllOf(aMarkAsOverdueCommand()));
     *        // or, to match against the payload of a Command Message only
     *        .expectDispatchedCommandsMatching(Matchers.payloadsMatching(Matchers.listWithAllOf(aMarkAsOverdueCommand())));
     * </pre>
     */
    @Test
    public void testCommandHandlerCase() throws IOException {
//        OrderCreatedEvent orderCreatedEvent = new ObjectMapper().readValue("{\"customerId\": \"19c48f1b-338d-49d3-b011-7d11d5189560\", \"title\": \"order_1\", \"ticketId\": \"6fcf920c-e600-43a8-8467-0acfd5144f88\", \"amount\": 100}", OrderCreatedEvent.class);
//        fixture.givenAggregate("19c48f1b-338d-49d3-b011-7d11d5189560")
//                .published()
//                .expectActiveSagas(1)
//                .expectDispatchedCommands(new OrderPayCommand(orderCreatedEvent.getOrderId(), orderCreatedEvent.getCustomerId(),orderCreatedEvent.getAmount()));
//                .when(new OrderPayCommand("oId1", "tId1", 100.0D))
//                .expectEvents(new OrderPayFailedEvent("oId1"));
//        System.out.println(when);

//        FixtureConfiguration<InvoicingSaga> fixture = new SagaTestFixture<>(InvoicingSaga.class);
//        fixture.givenAggregate(invoiceId).published(new InvoiceCreatedEvent())
//                .whenTimeElapses(Duration.ofDays(31))
//                .expectDispatchedCommandsMatching(Matchers.listWithAllOf(aMarkAsOverdueCommand()));
//// or, to match against the payload of a Command Message only .expectDispatchedCommandsMatching(Matchers.payloadsMatching(Matchers.listWithAllOf(aMarkAsOverdueCommand())));
    }

}
