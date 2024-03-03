package com.epam.demo.grocery.grocery.service;

import com.epam.demo.grocery.grocery.data.CategoryRepository;
import com.epam.demo.grocery.grocery.models.CategoryEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository repo;


    public CategoryService(CategoryRepository repo) {
        this.repo = repo;
    }

    List<CategoryEntity> getAllCategories() {
        return repo.findAll();
    }

    Optional<CategoryEntity> getCategory(Long categoryId)   {
        return repo.findById(categoryId);
    }
}
