package tech.api.heroapi.kafka.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import tech.api.heroapi.kafka.model.HeroCreateEventModel;

@Slf4j
@Component
public class HeroCreateEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value(value = "${spring.kafka.topic}")
    private String topic;

    public HeroCreateEventProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEvent(HeroCreateEventModel heroCreateEventModel) {
        kafkaTemplate.send(topic, heroCreateEventModel);
    }
}
