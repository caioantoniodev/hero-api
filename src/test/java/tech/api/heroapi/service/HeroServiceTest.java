package tech.api.heroapi.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.api.heroapi.entity.Heroes;
import tech.api.heroapi.entity.enums.HeroAlignmentEnum;
import tech.api.heroapi.kafka.model.HeroCreateEventModel;
import tech.api.heroapi.kafka.producer.HeroCreateEventProducer;
import tech.api.heroapi.repository.HeroRepository;
import tech.api.heroapi.rest.controller.model.HeroRequest;
import tech.api.heroapi.rest.controller.model.HeroResponse;

import java.util.List;
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

        Assertions.assertEquals("NOTIFY_TO_CREATE_HERO", heroCreateEventModel.getEventType());
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
}
