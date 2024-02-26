package com.example.gradlekafkatest.mapper;

import com.example.gradlekafkatest.config.MapperConfig;
import com.example.gradlekafkatest.dto.BookDto;
import com.example.gradlekafkatest.dto.BookDtoWithoutCategoryIds;
import com.example.gradlekafkatest.dto.CreateBookRequestDto;
import com.example.gradlekafkatest.model.Book;
import com.example.gradlekafkatest.model.Category;
import java.util.Optional;
import java.util.stream.Collectors;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class, uses = CategoryMapper.class)
public interface BookMapper {

    @Mapping(target = "categoryIds", ignore = true)
    BookDto toDto(Book book);

    BookDtoWithoutCategoryIds toDtoWithoutCategories(Book book);

    @AfterMapping
    default void setCategoryIds(@MappingTarget BookDto bookDto, Book book) {
        bookDto.setCategoryIds(book.getCategories().stream()
                .map(Category::getId)
                .collect(Collectors.toUnmodifiableSet()));
    }

    Book toModel(CreateBookRequestDto requestDto);

    @AfterMapping
    default void setCategories(@MappingTarget Book book, CreateBookRequestDto requestDto) {
        if (requestDto.categoryIds() == null) {
            return;
        }
        book.setCategories(requestDto.categoryIds().stream()
                .map(Category::new)
                .collect(Collectors.toUnmodifiableSet()));
    }

    @Named("bookById")
    default Book bookFromId(Long id) {
        return Optional.ofNullable(id)
                .map(Book::new)
                .orElse(null);
    }
}
