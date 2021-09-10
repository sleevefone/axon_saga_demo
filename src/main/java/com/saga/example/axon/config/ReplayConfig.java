package com.saga.example.axon.config;

import org.axonframework.config.Configuration;
import org.axonframework.config.EventProcessingConfigurer;
import org.axonframework.eventhandling.pooled.PooledStreamingEventProcessor;

import java.time.Duration;

/**
 * @author fanhb on 2021/9/10
 */
public class ReplayConfig implements EventProcessingConfigurer.PooledStreamingProcessorConfiguration {
    @Override

    public PooledStreamingEventProcessor.Builder apply(Configuration config, PooledStreamingEventProcessor.Builder builder) {

        return builder.initialToken(messageSource -> messageSource.createTokenSince(Duration.ofDays(31)));
    }
}





