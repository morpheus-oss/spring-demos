package com.epam.demo.grocery.grocery.data;

import com.epam.demo.grocery.grocery.models.ProductEntity;
import com.epam.demo.grocery.grocery.pojo.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

}
