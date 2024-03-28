package org.naiomi.spring.kafka.services;

import org.naiomi.spring.kafka.config.KafkaProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

private final KafkaTemplate<String, String> kafkaTemplate;
private final KafkaProperties kafkaProperties;

public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate, KafkaProperties kafkaProperties) {
    this.kafkaTemplate = kafkaTemplate;
    this.kafkaProperties = kafkaProperties;
}

    public void sendMessage(String message) {
        kafkaTemplate.send(kafkaProperties.getTopicName(), message);
    }
}
