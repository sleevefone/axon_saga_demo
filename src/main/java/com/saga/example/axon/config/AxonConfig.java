package com.saga.example.axon.config;

import org.axonframework.common.jpa.EntityManagerProvider;
import org.axonframework.common.transaction.TransactionManager;
import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.eventhandling.*;
import org.axonframework.eventhandling.async.SequencingPolicy;
import org.axonframework.eventhandling.async.SequentialPolicy;
import org.axonframework.eventhandling.pooled.PooledStreamingEventProcessor;
import org.axonframework.eventhandling.scheduling.EventScheduler;
import org.axonframework.eventhandling.scheduling.java.SimpleEventScheduler;
import org.axonframework.messaging.StreamableMessageSource;
import org.axonframework.spring.config.AxonConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

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
        return SimpleEventScheduler.builder()
                .eventBus(eventBus)
                .transactionManager(transactionManager)
                .scheduledExecutorService(Executors.newScheduledThreadPool(1))
                .build();
//        return new SimpleEventScheduler(Executors.newScheduledThreadPool(1), eventBus, transactionManager);
    }

    @Bean
    public SequencingPolicy<EventMessage<?>> mySequencingPolicy() {
         return (SequencingPolicy) new SequentialPolicy();
    }

    @Autowired
    public void configureInitialTrackingToken(EventProcessingConfigurer processingConfigurer) {
        EventProcessingConfigurer.PooledStreamingProcessorConfiguration psepConfig = new ReplayConfig();
        processingConfigurer.registerPooledStreamingEventProcessorConfiguration("customerProjector", psepConfig);
    }

//    @Autowired
//    public void configureInitialTrackingToken2(EventProcessingConfigurer processingConfigurer) {
//        TrackingEventProcessorConfiguration tepConfig =
//                TrackingEventProcessorConfiguration.forSingleThreadedProcessing()
//                        .andInitialTrackingToken(StreamableMessageSource::createTailToken);
//
//        processingConfigurer.registerTrackingEventProcessorConfiguration("my-processor", config -> tepConfig);
//    }
//
//    @Autowired
//    public void configureTokenClaimValues(EventProcessingConfigurer processingConfigurer) {
//        TrackingEventProcessorConfiguration tepConfig = TrackingEventProcessorConfiguration.forSingleThreadedProcessing()
//                        .andTokenClaimInterval(1000, TimeUnit.MILLISECONDS)
//                        .andEventAvailabilityTimeout(2000, TimeUnit.MILLISECONDS);
//
//        processingConfigurer.registerTrackingEventProcessorConfiguration("my-processor", config -> tepConfig);
//    }

}
