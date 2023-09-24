package tech.api.heroapi.kafka.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class HeroCreateEventModel {

    private String name;
    private String power;
    private String alignment;
}
