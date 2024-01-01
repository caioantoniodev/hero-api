package tech.api.heroapi.kafka.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tech.api.heroapi.entity.Heroes;
import tech.api.heroapi.entity.enums.HeroAlignmentEnum;
import tech.api.heroapi.kafka.model.HeroCreateEventModel;
import tech.api.heroapi.repository.HeroRepository;

@Slf4j
@RequiredArgsConstructor
@Component
public class HeroCreateEventConsumer {

    private final HeroRepository heroRepository;

    @KafkaListener(topics = "${spring.kafka.topic}", containerFactory = "heroCreateEventListenerFactory")
    void consumeEvent(HeroCreateEventModel heroCreateEventModel) {
        log.info("Hero consumed {} ", heroCreateEventModel);

        var hero = Heroes.builder()
                .alignment(HeroAlignmentEnum.valueOf(heroCreateEventModel.getAlignment()))
                .power(heroCreateEventModel.getPower())
                .name(heroCreateEventModel.getName())
                .build();

        var heroPersisted = heroRepository.save(hero);

        log.info("Hero persisted {}", heroPersisted);
    }
}
