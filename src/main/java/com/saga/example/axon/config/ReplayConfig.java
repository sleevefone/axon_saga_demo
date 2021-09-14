package com.saga.example.axon.config;

import org.axonframework.config.Configuration;
import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.eventhandling.TrackedEventMessage;
import org.axonframework.eventhandling.TrackingToken;
import org.axonframework.eventhandling.pooled.PooledStreamingEventProcessor;
import org.axonframework.messaging.StreamableMessageSource;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;

/**
 * @author fanhb on 2021/9/10
 */
public class ReplayConfig implements EventProcessingConfigurer.PooledStreamingProcessorConfiguration {
    @Override
    public PooledStreamingEventProcessor.Builder apply(Configuration config, PooledStreamingEventProcessor.Builder builder) {
        return builder.initialToken(messageSource -> messageSource.createTokenSince(Duration.ofDays(31)));
    }
}





