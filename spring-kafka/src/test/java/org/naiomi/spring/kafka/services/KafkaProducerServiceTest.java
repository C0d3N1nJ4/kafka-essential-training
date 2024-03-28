package org.naiomi.spring.kafka.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.naiomi.spring.kafka.config.KafkaProperties;
import org.springframework.kafka.core.KafkaTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class KafkaProducerServiceTest {

    @Mock
    private KafkaTemplate<String, String> kafkaTemplate;

    @Mock
    private KafkaProperties kafkaProperties;

    @InjectMocks
    KafkaProducerService kafkaProducerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initializes mocks
    }

    @Test
    void testSendMessage() {
        // Arrange
        String testMessage = "Hello, Kafka!";
        String testTopicName = "spring_topic";
        // When `getTopicName` is called, return `testTopicName`
        when(kafkaProperties.getTopicName()).thenReturn(testTopicName);

        // Act
        kafkaProducerService.sendMessage(testMessage);

        // Assert
        // Verify that `send` method was called with correct parameters
        verify(kafkaTemplate).send(eq(testTopicName), eq(testMessage));
    }

}