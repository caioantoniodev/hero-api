package tech.api.heroapi.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.api.heroapi.domain.Heroes;
import tech.api.heroapi.domain.enums.HeroAlignmentEnum;
import tech.api.heroapi.kafka.model.HeroCreateEventModel;
import tech.api.heroapi.kafka.producer.HeroCreateEventProducer;
import tech.api.heroapi.repository.HeroRepository;
import tech.api.heroapi.rest.controller.model.HeroRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HeroServiceTest {

    @InjectMocks
    private HeroService heroService;

    @Mock
    private HeroCreateEventProducer heroCreateEventProducer;

    @Mock
    private HeroRepository heroRepository;

    @Captor
    ArgumentCaptor<HeroCreateEventModel> heroCreateEventModelArgumentCaptor;

    @Test
    void shouldBeSendHeroCreateEventWhenIsValid() {
        var heroRequest = new HeroRequest(
                "Spider Man",
                "Wall-crawling, web-slinging, spider-sense",
                "HERO");

        heroService.startCreateHero(heroRequest);

        verify(heroCreateEventProducer, only()).sendEvent(heroCreateEventModelArgumentCaptor.capture());

        var heroCreateEventModel = heroCreateEventModelArgumentCaptor.getValue();

        Assertions.assertEquals("NOTIFY_TO_CREATE_HERO", heroCreateEventModel.eventType());
        Assertions.assertEquals(heroRequest.name(), heroCreateEventModel.name());
        Assertions.assertEquals(heroRequest.power(), heroCreateEventModel.power());
        Assertions.assertEquals(heroRequest.alignment(), heroCreateEventModel.alignment());
    }

    @Test
    void shouldBeReturnAllHeroes() {
        var heroes = Heroes.builder()
                .id(UUID.randomUUID())
                .alignment(HeroAlignmentEnum.HERO)
                .name("Spider Man")
                .power("Wall-crawling, web-slinging, spider-sense")
                .build();

        when(heroRepository.findAll()).thenReturn(List.of(heroes));

        var firstHeroResponse = heroService.getHeroes().get(0);

        Assertions.assertNotNull(firstHeroResponse);
        Assertions.assertEquals(heroes.getId(), firstHeroResponse.id());
    }

    @Test
    void shouldBeReturnHeroById() {
        var heroId = UUID.randomUUID();

        var heroes = Heroes.builder()
                .id(heroId)
                .alignment(HeroAlignmentEnum.HERO)
                .name("Spider Man")
                .power("Wall-crawling, web-slinging, spider-sense")
                .build();

        when(heroRepository.findById(any(UUID.class))).thenReturn(Optional.ofNullable(heroes));

        var hero = heroService.getHero(String.valueOf(heroId));

        Assertions.assertNotNull(hero);
        Assertions.assertEquals(heroes.getId(), hero.id());
    }

    @Test
    void shouldBeDeleteHeroById() {
        var heroId = UUID.randomUUID();

        var heroes = Heroes.builder()
                .id(heroId)
                .alignment(HeroAlignmentEnum.HERO)
                .name("Spider Man")
                .power("Wall-crawling, web-slinging, spider-sense")
                .build();

        when(heroRepository.findById(any(UUID.class))).thenReturn(Optional.ofNullable(heroes));

        heroService.removeHero(String.valueOf(heroId));

        verify(heroRepository, times(1)).delete(heroes);
    }
}
