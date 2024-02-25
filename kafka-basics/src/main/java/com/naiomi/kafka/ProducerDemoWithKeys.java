package com.naiomi.kafka;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProducerDemoWithKeys {

private static final Logger log = LoggerFactory.getLogger(ProducerDemoWithKeys.class);
    public static void main(String[] args) {
        log.info ("This is a producer with keys");

        //Create producer properties
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "127.0.0.1:9092");
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());

        //Create the producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        for (int i = 0; i < 10; i++) {

            String topic = "first_topic";
            String value = "Producer with keys " + i;
            String key = "id_" + i;

            //Create a producer record
            ProducerRecord<String, String> record = new ProducerRecord<>(topic, value + i);

            //Send data
            producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {

                    if (exception == null) {
                        log.info("Received new metadata. \n" +
                                "Key: " + key + "\n" +
                                "Partition: " + metadata.partition());
                    } else {
                        log.error("Error while producing", exception);
                    }
                }
            });
        }

        //Flush and close producer
        producer.flush();

        //Close producer
        producer.close();
    }
}
