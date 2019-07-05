package com.example.service;

import java.util.List;
import java.util.Optional;

import com.example.model.Products;

public interface ProductService {

	List<Products> findAllProduct();
    Optional<Products> findById(Integer id);
    void save(Products product);
    void remove(Integer id);
 
}
