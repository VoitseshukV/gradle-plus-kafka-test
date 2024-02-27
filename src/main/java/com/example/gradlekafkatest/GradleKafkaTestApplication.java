package com.example.gradlekafkatest;

import com.example.gradlekafkatest.kafka.KafkaBookConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class}
)
@EnableAsync
public class GradleKafkaTestApplication {

    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("-c")) {
            KafkaBookConsumer consumer = new KafkaBookConsumer();
            consumer.receiveMessages();
        } else {
            SpringApplication.run(GradleKafkaTestApplication.class, args);
        }
    }

}
