package com.epam.demo.grocery.grocery.data;

import com.epam.demo.grocery.grocery.models.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
}
