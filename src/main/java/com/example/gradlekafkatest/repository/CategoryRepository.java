package com.example.gradlekafkatest.repository;

import com.example.gradlekafkatest.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}

