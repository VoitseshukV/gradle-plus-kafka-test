package com.example.gradlekafkatest.kafka.serializers;

import com.example.gradlekafkatest.dto.BookDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import org.apache.kafka.common.serialization.Deserializer;

public class BookDeserializer implements Deserializer {

     @Override
    public void configure(Map map, boolean b) {
    }

    @Override
    public BookDto deserialize(String s, byte[] bytes) {
        ObjectMapper mapper = new ObjectMapper();
        BookDto person = null;
        try {
            person = mapper.readValue(bytes, BookDto.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return person;
    }

    @Override
    public void close() {
    }
}