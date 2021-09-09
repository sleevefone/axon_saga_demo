//package com.saga.example.axon;
//
//import org.axonframework.modelling.command.AggregateNotFoundException;
//import org.axonframework.test.aggregate.AggregateTestFixture;
//import org.axonframework.test.aggregate.FixtureConfiguration;
//import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.extension.*;
//import org.mockito.junit.jupiter.*;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//
//@ExtendWith(MockitoExtension.class)
//public class OrderPaidTest {
//
//    private static ArrayList<OrderItem> items;
//    private FixtureConfiguration<OrderPaid> fixture = new AggregateTestFixture<>(OrderPaid.class);
//
//    @BeforeAll
//    static void before() {
//        items = new ArrayList<>();
//        items.add(new OrderItem("id1", "name1", 1L, BigDecimal.TEN));
//        items.add(new OrderItem("id2", "name2", 3L, BigDecimal.ONE));
//    }
//
//    @Test
//    public void orderPaidTest() {
//        OrderPaidEvent orderPaidEvent = new OrderPaidEvent("aggregate-order1", "order1", OrderStatus.NEW, items);
//        MarkOrderAsDeliveredCommand markOrderAsDeliveredCommand = new MarkOrderAsDeliveredCommand("aggregate-order1");
//        OrderDeliveryInitiatedEvent orderDeliveryInitiatedEvent = new OrderDeliveryInitiated
//        OrderDeliveredEvent orderDeliveredEvent = new OrderDeliveredEvent("aggregate-order1","order1", OrderStatus.PAID, items);
//
//        fixture.given(orderPaidEvent)
//               .when(markOrderAsDeliveredCommand)
//               .expectEvents(orderDeliveryInitiatedEvent, orderDeliveredEvent)
//               .expectMarkedDeleted();
//    }
//
//    @Test
//    public void orderAddItemFailedTest() {
//        OrderPaidEvent orderPaidEvent = new OrderPaidEvent("aggregate-order1", "order1", OrderStatus.NEW, items);
//        AddItemToTheOrderCommand addItemToTheOrderCommand = new AddItemToTheOrderCommand("aggregate-order1","order1",new OrderItem("i3", "name3", 36L, BigDecimal.ONE));
//        UnsupportedOperationEvent unsupportedOperationEvent = new UnsupportedOperationEvent("aggregate-order1", "order1", AddItemToTheOrderCommand.class);
//
//        fixture.given(orderPaidEvent)
//               .when(addItemToTheOrderCommand)
//               .expectEvents(unsupportedOperationEvent);
//    }
//
//    @Test
//    public void orderCanceledTest() {
//
//        OrderPaidEvent orderPaidEvent = new OrderPaidEvent("aggregate-order1", "order1", OrderStatus.NEW, items);
//        MarkOrderAsCancelledCommand markOrderAsCancelledCommand = new MarkOrderAsCancelledCommand("aggregate-order1");
//        OrderCancellationInitiatedEvent orderCancellationInitiatedEvent = new OrderCancellationInitiatedEvent("aggregate-order1");
//        OrderCanceledEvent orderCanceledEvent = new OrderCanceledEvent("", "order1", OrderStatus.PAID, items);
//
//        fixture.given(orderPaidEvent)
//               .when(markOrderAsCancelledCommand)
//               .expectSuccessfulHandlerExecution()
//               .expectEvents(orderCancellationInitiatedEvent, orderCanceledEvent)
//               .expectMarkedDeleted();
//    }
//
//    @Test
//    public void orderNotCanceled_AggregateNotFoundExceptionTest() {
//        MarkOrderAsCancelledCommand markOrderAsCancelledCommand = new MarkOrderAsCancelledCommand("aggregate-order1");
//
//        fixture.given()
//               .when(markOrderAsCancelledCommand)
//               .expectException(AggregateNotFoundException.class);
//    }
//
//}
