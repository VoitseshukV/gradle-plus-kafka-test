package com.example.gradlekafkatest.service.impl;

import com.example.gradlekafkatest.dto.CategoryDto;
import com.example.gradlekafkatest.dto.CreateCategoryRequestDto;
import com.example.gradlekafkatest.exception.EntityNotFoundException;
import com.example.gradlekafkatest.mapper.CategoryMapper;
import com.example.gradlekafkatest.model.Category;
import com.example.gradlekafkatest.repository.CategoryRepository;
import com.example.gradlekafkatest.service.CategoryService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryDto save(CreateCategoryRequestDto requestDto) {
        return categoryMapper.toDto(categoryRepository.save(
                categoryMapper.toModel(requestDto)));
    }

    @Override
    public List<CategoryDto> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable).stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    @Override
    public CategoryDto getById(Long id) {
        return categoryMapper.toDto(categoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't get category with id: " + id)));
    }

    @Override
    public CategoryDto updateById(Long id, CreateCategoryRequestDto requestDto) {
        if (!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException("Can't get category with id: " + id);
        }
        Category category = categoryMapper.toModel(requestDto);
        category.setId(id);
        return categoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }
}
