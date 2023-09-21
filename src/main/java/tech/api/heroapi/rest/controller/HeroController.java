package tech.api.heroapi.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.api.heroapi.kafka.producer.HeroCreateEventProducer;

@RestController
public class HeroController {

    private final HeroCreateEventProducer heroCreateEventProducer;

    public HeroController(HeroCreateEventProducer heroCreateEventProducer) {
        this.heroCreateEventProducer = heroCreateEventProducer;
    }

    @PostMapping
    ResponseEntity<?> createHero() {
        heroCreateEventProducer.sendEvent();

        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
