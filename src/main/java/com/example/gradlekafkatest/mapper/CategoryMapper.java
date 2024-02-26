package com.example.gradlekafkatest.mapper;

import com.example.gradlekafkatest.config.MapperConfig;
import com.example.gradlekafkatest.dto.CategoryDto;
import com.example.gradlekafkatest.dto.CreateCategoryRequestDto;
import com.example.gradlekafkatest.model.Category;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface CategoryMapper {
    CategoryDto toDto(Category category);

    Category toModel(CreateCategoryRequestDto requestDto);
}
