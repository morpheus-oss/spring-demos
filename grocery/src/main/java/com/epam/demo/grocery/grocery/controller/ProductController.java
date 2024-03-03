package com.epam.demo.grocery.grocery.controller;

import com.epam.demo.grocery.grocery.models.ProductEntity;
import com.epam.demo.grocery.grocery.pojo.Product;
import com.epam.demo.grocery.grocery.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductEntity> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    void addProduct(Product product)  {

    }

    void updateProduct(Product product)  {

    }


}
