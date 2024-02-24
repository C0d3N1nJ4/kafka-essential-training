package com.naiomi.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProducerDemo {

private static final Logger log = LoggerFactory.getLogger(ProducerDemo.class);
    public static void main(String[] args) {
        log.info ("This is a producer");

        //Create producer properties
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "127.0.0.1:9092");
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());

        //Create the producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        //Create a producer record
        ProducerRecord<String, String> record = new ProducerRecord<>("first_topic", "Hello Producer");

        //Send data
        producer.send(record);

        //Flush and close producer
        producer.flush();

        //Close producer
        producer.close();
    }
}
