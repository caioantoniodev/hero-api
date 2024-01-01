package tech.api.heroapi.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.api.heroapi.kafka.model.HeroCreateEventModel;
import tech.api.heroapi.kafka.producer.HeroCreateEventProducer;
import tech.api.heroapi.repository.HeroRepository;
import tech.api.heroapi.rest.controller.model.HeroRequest;

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
}
