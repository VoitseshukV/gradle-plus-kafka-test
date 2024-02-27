package com.example.gradlekafkatest.kafka;

import com.example.gradlekafkatest.config.KafkaTopicConfig;
import com.example.gradlekafkatest.dto.BookDto;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.LongDeserializer;

public class KafkaBookConsumer {
    public void receiveMessages() {
        try (Consumer<Long, BookDto> consumer = getConsumer()) {
            consumer.subscribe(List.of(KafkaTopicConfig.BOOKS_TOPIC));
            while (true) {
                final ConsumerRecords<Long, BookDto> consumerRecords = consumer.poll(Duration.ofSeconds(1));
                if (consumerRecords.count() == 0) {
                    continue;
                }
                consumerRecords.forEach(record -> {
                    System.out.printf("Consumer Record:(%s, %s, %d, %d)\n",
                            record.key(),
                            record.value().toString(),
                            record.partition(), record.offset());
                });
                consumer.commitAsync();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public KafkaConsumer<Long, BookDto> getConsumer(
    ) {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "books");
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "10");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                "com.example.gradlekafkatest.kafka.serializers.BookDeserializer");
        return new KafkaConsumer<>(props);
    }
}
