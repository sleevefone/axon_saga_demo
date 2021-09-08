package com.saga.example.axon.config;

import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.scheduling.EventScheduler;
import org.axonframework.eventhandling.scheduling.java.SimpleEventScheduler;
import org.axonframework.spring.config.AxonConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.concurrent.Executors;

/**
 * Created by mavlarn on 2018/6/1.
 */
@Configuration
public class AxonConfig {

    @Resource
    AxonConfiguration axonConfiguration;

//    注册一个独立的CommandHandler类的方法
//    @Bean
//    public OrderCommandHandler orderCommandHandler() {
//        Repository<Order> orderRepository = axonConfiguration.repository(Order.class);
//        Repository<Customer> customerRepository = axonConfiguration.repository(Customer.class);
//        return new OrderCommandHandler(orderRepository, customerRepository);
//    }

    @Bean
    public EventScheduler eventScheduler(EventBus eventBus, TransactionManager transactionManager) {
        return new SimpleEventScheduler(Executors.newScheduledThreadPool(1), eventBus, transactionManager);
    }
}
