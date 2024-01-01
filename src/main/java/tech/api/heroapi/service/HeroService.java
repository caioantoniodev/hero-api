package tech.api.heroapi.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.api.heroapi.entity.Heroes;
import tech.api.heroapi.kafka.model.HeroCreateEventModel;
import tech.api.heroapi.kafka.producer.HeroCreateEventProducer;
import tech.api.heroapi.repository.HeroRepository;
import tech.api.heroapi.rest.controller.model.HeroRequest;
import tech.api.heroapi.rest.controller.model.HeroResponse;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class HeroService {

    private final HeroCreateEventProducer heroCreateEventProducer;
    private final HeroRepository heroRepository;

    public void startCreateHero(HeroRequest heroRequest) {
        log.info("received {}", heroRequest);

        var heroCreateEventModel = HeroCreateEventModel.builder()
                .eventId(UUID.randomUUID().toString())
                .eventType("NOTIFY_TO_CREATE_HERO")
                .eventTime(ZonedDateTime.now())
                .name(heroRequest.name())
                .alignment(heroRequest.alignment())
                .power(heroRequest.power()).build();

        heroCreateEventProducer.sendEvent(heroCreateEventModel);
    }

    public HeroResponse getHero(String id) {
        var heroId = UUID.fromString(id);
        var heroes = this.findHero(heroId);

        return new HeroResponse(heroes.getId(),
                heroes.getName(),
                heroes.getPower(),
                String.valueOf(heroes.getAlignment()),
                heroes.getCreatedAt(),
                heroes.getUpdatedAt());
    }

    public List<HeroResponse> getHeroes() {
        var heroes = heroRepository.findAll();

        return heroes.stream()
                .map(hero -> new HeroResponse(hero.getId(),
                        hero.getName(),
                        hero.getPower(),
                        String.valueOf(hero.getAlignment()),
                        hero.getCreatedAt(),
                        hero.getUpdatedAt()))
                .toList();
    }

    public void removeHero(String id) {
        var heroId = UUID.fromString(id);
        var hero = this.findHero(heroId);

        heroRepository.delete(hero);
    }

    private Heroes findHero(UUID heroId) {
        return heroRepository.findById(heroId).orElseThrow();
    }
}
