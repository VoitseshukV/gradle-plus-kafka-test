package com.example.gradlekafkatest.service;

import com.example.gradlekafkatest.dto.CategoryDto;
import com.example.gradlekafkatest.dto.CreateCategoryRequestDto;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    CategoryDto save(CreateCategoryRequestDto requestDto);

    List<CategoryDto> findAll(Pageable pageable);

    CategoryDto getById(Long id);

    CategoryDto updateById(Long id, CreateCategoryRequestDto requestDto);

    void deleteById(Long id);
}
