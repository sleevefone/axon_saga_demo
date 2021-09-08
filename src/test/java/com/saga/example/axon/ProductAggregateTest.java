//package com.saga.example.axon;
//
//import com.saga.example.axon.ticket.Ticket;
//import org.axonframework.test.aggregate.AggregateTestFixture;
//import org.axonframework.test.saga.FixtureConfiguration;
//import org.junit.Before;
//import org.junit.Test;
//
///**
// * {@link org.axonframework.test.aggregate.FixtureConfiguration}
// *
// *
// */
//public class ProductAggregateTest {
//
//    private AggregateTestFixture<Ticket> fixture;
//
//    @Before
//    public void setUp() throws Exception {
//
//        fixture = new AggregateTestFixture<>(Ticket.class);
//
//    }
//
//    @Test
//    public void testAddProduct() throws Exception {
//        fixture.given()
//                .when(new AddProductCommand("product-1", "product name"))
//                .expectEvents(new ProductAddedEvent("product-1", "product name"));
//    }
//
//
//}