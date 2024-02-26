package com.example.gradlekafkatest.service;

import com.example.gradlekafkatest.dto.BookDto;
import com.example.gradlekafkatest.dto.BookDtoWithoutCategoryIds;
import com.example.gradlekafkatest.dto.BookSearchParametersDto;
import com.example.gradlekafkatest.dto.CreateBookRequestDto;
import com.example.gradlekafkatest.model.Book;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookDto save(CreateBookRequestDto requestDto);

    List<BookDto> findAll(Pageable pageable);

    BookDto getDtoById(Long id);

    Book getById(Long id);

    BookDto updateById(Long id, CreateBookRequestDto requestDto);

    void deleteById(Long id);

    List<BookDtoWithoutCategoryIds> findAllByCategoryId(Long id);
}
