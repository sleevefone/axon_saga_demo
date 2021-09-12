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

        return builder.initialToken(new Function<StreamableMessageSource<TrackedEventMessage<?>>, TrackingToken>() {
                                        @Override
                                        public TrackingToken apply(StreamableMessageSource<TrackedEventMessage<?>> messageSource) {

                                            //                            messageSource.openStream()
//                                        messageSource.
//                                            Instant instant = LocalDateTime.parse("20210101 00:00:00", DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss")).toInstant(ZoneOffset.UTC);
                                            TrackingToken tokenSince = messageSource.createTokenSince(Duration.ofDays(31));
//                                                                        messageSource.openStream()

//                                            messageSource.createHeadToken();
//                                            return messageSource.createTokenAt(instant);
                                            return tokenSince;
                                        }
                                    }
        );
    }
}





