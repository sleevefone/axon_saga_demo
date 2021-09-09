package com.saga.example.axon.config;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.EventProcessingConfiguration;
import org.axonframework.eventhandling.GapAwareTrackingToken;
import org.axonframework.eventhandling.TrackingEventProcessor;
import org.axonframework.eventhandling.tokenstore.UnableToClaimTokenException;
import org.springframework.stereotype.Service;

import java.util.Collections;


/**
 * @author fanhaibo
 * link: https://blog.codecentric.de/en/2019/12/axon-replaying-made-easy-with-endpoints/
 *
 */
@Service
@Slf4j
public class TrackingEventProcessorService {


    private final EventProcessingConfiguration eventProcessingConfiguration;

    public TrackingEventProcessorService(EventProcessingConfiguration eventProcessingConfiguration) {
        this.eventProcessingConfiguration = eventProcessingConfiguration;
    }

    private TrackingEventProcessor getTrackingEventProcessor(String name) {
        return this.eventProcessingConfiguration
                .eventProcessor(name, TrackingEventProcessor.class).
                orElseThrow(RuntimeException::new);
    }

    public boolean replay(String trackingEventProcessorName, Long index) {
        TrackingEventProcessor trackingEventProcessor = this.getTrackingEventProcessor(trackingEventProcessorName);
        if (!trackingEventProcessor.isRunning()) {
            log.warn("Tracking event processor {} is not running in current instance or not running at all", trackingEventProcessorName);
            return false;
        }
        trackingEventProcessor.shutDown();
        try {
            trackingEventProcessor.resetTokens(GapAwareTrackingToken.newInstance(index - 1, Collections.emptySortedSet()));
        } catch (UnableToClaimTokenException e) { // Ignore this exception and let the caller know setting the replay failed.
            log.warn("Unable to claim token for trackingEventProcessor {} on id {}", trackingEventProcessorName, index - 1, e);
            return false;
        } finally {
            log.info("Starting replay for trackingEventProcessor {} on id {}", trackingEventProcessorName, index - 1);
            trackingEventProcessor.start();
        }
        return true;
    }
}