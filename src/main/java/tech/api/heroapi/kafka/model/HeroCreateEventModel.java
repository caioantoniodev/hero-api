package tech.api.heroapi.kafka.model;

import java.time.ZonedDateTime;

public record HeroCreateEventModel(String eventId,
                                   String eventType,
                                   ZonedDateTime eventTime,
                                   String name,
                                   String power,
                                   String alignment) {
}
