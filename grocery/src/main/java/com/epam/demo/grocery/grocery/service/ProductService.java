package com.epam.demo.grocery.grocery.service;

import com.epam.demo.grocery.grocery.data.ProductRepository;
import com.epam.demo.grocery.grocery.models.ProductEntity;
import com.epam.demo.grocery.grocery.pojo.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository repository;

    ProductService(ProductRepository productRepo)   {
        this.repository = productRepo;
    }

    public List<ProductEntity> getAllProducts()  {
        return repository.findAll();
    }

    public Optional<ProductEntity> getProduct(Long productId)   {
        return repository.findById(productId);
    }
}
