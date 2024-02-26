package com.example.gradlekafkatest.repository;

import com.example.gradlekafkatest.model.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {
    @Query(value = "SELECT b FROM Book b JOIN b.categories bc WHERE bc.id = :id")
    List<Book> findAllByCategoryId(Long id);
}

