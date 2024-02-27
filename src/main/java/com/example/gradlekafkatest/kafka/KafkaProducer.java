package com.example.gradlekafkatest.kafka;

import com.example.gradlekafkatest.config.KafkaTopicConfig;
import com.example.gradlekafkatest.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class KafkaProducer {
    private final ProducerFactory<Long, BookDto> producerFactory;

    public void send(BookDto book) {
        try (Producer<Long, BookDto> producer = producerFactory.createProducer()) {
            ProducerRecord<Long, BookDto> record = new ProducerRecord<>(KafkaTopicConfig.BOOKS_TOPIC, book.getId(), book);
            producer.send(record,
                    (metadata, exception) -> {
                        if (exception == null) {
                            System.out.println("Message sent successfully: " + metadata);
                        } else {
                            System.err.println("Error sending message: " + exception.getMessage());
                        }
                    });
            System.out.println("Record sent: " + book.getTitle());
        }
    }
}
