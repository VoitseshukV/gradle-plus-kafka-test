package com.example.gradlekafkatest.service.impl;

import com.example.gradlekafkatest.dto.BookDto;
import com.example.gradlekafkatest.dto.BookDtoWithoutCategoryIds;
import com.example.gradlekafkatest.dto.CreateBookRequestDto;
import com.example.gradlekafkatest.exception.EntityNotFoundException;
import com.example.gradlekafkatest.kafka.KafkaProducer;
import com.example.gradlekafkatest.mapper.BookMapper;
import com.example.gradlekafkatest.model.Book;
import com.example.gradlekafkatest.repository.BookRepository;
import com.example.gradlekafkatest.repository.CategoryRepository;
import com.example.gradlekafkatest.service.BookService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final BookMapper bookMapper;
    private final KafkaProducer producer;

    @Override
    public BookDto save(CreateBookRequestDto createBookDto) {
        BookDto bookDto = bookMapper.toDto(bookRepository.save(bookMapper.toModel(createBookDto)));
        producer.send(bookDto);
        return bookDto;
    }

    @Override
    public List<BookDto> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable).stream()
                .map(bookMapper::toDto)
                .toList();
    }

    @Override
    public BookDto getDtoById(Long id) {
        return bookMapper.toDto(getById(id));
    }

    @Override
    public Book getById(Long id) {
        return bookRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Can't get book with id: " + id));
    }

    @Override
    public BookDto updateById(Long id, CreateBookRequestDto bookDto) {
        if (!bookRepository.existsById(id)) {
            throw new EntityNotFoundException("Can't get book with id: " + id);
        }
        Book book = bookMapper.toModel(bookDto);
        book.setId(id);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookDtoWithoutCategoryIds> findAllByCategoryId(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new EntityNotFoundException("Can't get category with id: " + id);
        }
        return bookRepository.findAllByCategoryId(id).stream()
                .map(bookMapper::toDtoWithoutCategories)
                .toList();
    }
}
