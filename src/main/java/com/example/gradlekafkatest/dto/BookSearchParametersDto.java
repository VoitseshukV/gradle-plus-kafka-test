package com.example.gradlekafkatest.dto;

public record BookSearchParametersDto(
        String title,
        String author,
        String isbn,
        String description
) {
}
