package com.example.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.example.model.Product;
 

public interface ProductService {

	List<Product> findAllProduct();
    Optional<Product> findById(Integer id);
    void save(Product product);
    void remove(Integer id);
    
     
	List<Product> getAllEmployeesSortPage(Integer pageNo, Integer pageSize, String sortBy);
	
	//List<Product> getAllEmployeesSort(HashMap<String, String> sortBy);
	
	List<Product> getAllEmployeesMutiSort(String sortBy1, String sortBy2);
 
}
