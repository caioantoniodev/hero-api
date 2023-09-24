package tech.api.heroapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.api.heroapi.entity.Heroes;
import tech.api.heroapi.kafka.model.HeroCreateEventModel;
import tech.api.heroapi.kafka.producer.HeroCreateEventProducer;
import tech.api.heroapi.repository.HeroRepository;
import tech.api.heroapi.rest.controller.model.HeroRequest;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class HeroService {

    private final HeroCreateEventProducer heroCreateEventProducer;
    private final HeroRepository heroRepository;

    public void startCreateHero(HeroRequest heroRequest) {
        log.info("received [{}]", heroRequest);

        var heroCreateEventModel = HeroCreateEventModel.builder()
                .name(heroRequest.name())
                .alignment(heroRequest.alignment())
                .power(heroRequest.power()).build();

        heroCreateEventProducer.sendEvent(heroCreateEventModel);
    }

    public Heroes getHero(UUID uuid) {
        return heroRepository.findById(uuid).orElseThrow();
    }
}