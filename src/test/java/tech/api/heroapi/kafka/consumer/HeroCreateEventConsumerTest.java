package tech.api.heroapi.kafka.consumer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.api.heroapi.entity.Heroes;
import tech.api.heroapi.entity.enums.HeroAlignmentEnum;
import tech.api.heroapi.kafka.model.HeroCreateEventModel;
import tech.api.heroapi.repository.HeroRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class HeroCreateEventConsumerTest {

    @InjectMocks
    private HeroCreateEventConsumer heroCreateEventConsumer;

    @Mock
    private HeroRepository heroRepository;

    @Captor
    private ArgumentCaptor<Heroes> heroCaptor;

    @Test
    public void shouldBeConsumeEvent() {
        var heroCreateEventModel = new HeroCreateEventModel();
        heroCreateEventModel.setName("Superman");
        heroCreateEventModel.setPower("Flight");
        heroCreateEventModel.setAlignment("HERO");

        heroCreateEventConsumer.consumeEvent(heroCreateEventModel);

        verify(heroRepository).save(heroCaptor.capture());

        Heroes capturedHero = heroCaptor.getValue();

        assertEquals("Superman", capturedHero.getName());
        assertEquals("Flight", capturedHero.getPower());
        assertEquals(HeroAlignmentEnum.HERO, capturedHero.getAlignment());
    }
}