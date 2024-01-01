package tech.api.heroapi.kafka.model;

import lombok.*;

import java.time.ZonedDateTime;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class HeroCreateEventModel {

    private String eventId;
    private String eventType;
    private ZonedDateTime eventTime;
    private String name;
    private String power;
    private String alignment;
}
