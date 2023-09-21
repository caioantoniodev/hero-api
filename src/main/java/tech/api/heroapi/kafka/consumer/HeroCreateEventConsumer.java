package tech.api.heroapi.kafka.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import tech.api.heroapi.kafka.model.HeroCreateEventModel;

@Slf4j
@RequiredArgsConstructor
@Component
public class HeroCreateEventConsumer {


    @KafkaListener(topics = "${spring.kafka.topic}", containerFactory = "heroCreateEventListenerFactory")
    private void consumeEvent(HeroCreateEventModel heroCreateEventModel) {
        log.info("[{}]", heroCreateEventModel);
    }
}
