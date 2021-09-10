package com.saga.example.axon.customer.query;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.EventProcessingConfiguration;
import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.eventhandling.GapAwareTrackingToken;
import org.axonframework.eventhandling.TrackedEventMessage;
import org.axonframework.eventhandling.TrackingEventProcessor;
import org.axonframework.eventhandling.TrackingToken;
import org.axonframework.eventhandling.pooled.PooledStreamingEventProcessor;
import org.axonframework.eventhandling.tokenstore.UnableToClaimTokenException;
import org.axonframework.messaging.StreamableMessageSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.function.Function;


/**
 * @author fanhaibo
 * link: https://blog.codecentric.de/en/2019/12/axon-replaying-made-easy-with-endpoints/
 *
 */
@Service
@Slf4j
@AllArgsConstructor
public class TrackingEventProcessorService {

    private final EventProcessingConfiguration eventProcessingConfiguration;


    private TrackingEventProcessor getTrackingEventProcessor(String name) {
        return this.eventProcessingConfiguration
                .eventProcessor(name, TrackingEventProcessor.class).
                        orElseThrow(RuntimeException::new);
    }

    public boolean replay(String eventName, Long index) {
        TrackingEventProcessor trackingEventProcessor = this.getTrackingEventProcessor(eventName);
        if (!trackingEventProcessor.isRunning()) {
            log.warn("Tracking event processor {} is not running in current instance or not running at all", eventName);
            return false;
        }
        trackingEventProcessor.shutDown();
        try {
//            trackingEventProcessor.resetTokens();
            trackingEventProcessor.resetTokens(GapAwareTrackingToken.newInstance(index - 1, Collections.emptySortedSet()));


        } catch (UnableToClaimTokenException e) { // Ignore this exception and let the caller know setting the replay failed.
            log.warn("Unable to claim token for trackingEventProcessor {} on id {}", eventName, index - 1, e);
            return false;
        } finally {
            log.info("Starting replay for trackingEventProcessor {} on id {}", eventName, index - 1);
            trackingEventProcessor.start();
        }
        return true;
    }
//    private TrackingEventProcessor getTrackingEventProcessor(String name) {
//        return this.eventProcessingConfiguration
//                .eventProcessor(name, TrackingEventProcessor.class).
//                orElseThrow(RuntimeException::new);
//    }

//    public boolean replay(String eventName, Long index) {
//        TrackingEventProcessor trackingEventProcessor = this.getTrackingEventProcessor(eventName);
//        if (!trackingEventProcessor.isRunning()) {
//            log.warn("Tracking event processor {} is not running in current instance or not running at all", eventName);
//            return false;
//        }
//        trackingEventProcessor.shutDown();
//        try {
////            trackingEventProcessor.resetTokens();
//            trackingEventProcessor.resetTokens(GapAwareTrackingToken.newInstance(index - 1, Collections.emptySortedSet()));
//        } catch (UnableToClaimTokenException e) { // Ignore this exception and let the caller know setting the replay failed.
//            log.warn("Unable to claim token for trackingEventProcessor {} on id {}", eventName, index - 1, e);
//            return false;
//        } finally {
//            log.info("Starting replay for trackingEventProcessor {} on id {}", eventName, index - 1);
//            trackingEventProcessor.start();
//        }
//        return true;
//    }
}