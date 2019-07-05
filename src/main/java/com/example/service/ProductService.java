package com.example.service;

import java.util.List;
import java.util.Optional;

import com.example.model.Product;

public interface ProductService {

	List<Product> findAllProduct();
    Optional<Product> findById(Integer id);
    void save(Product product);
    void remove(Integer id);
 
}
