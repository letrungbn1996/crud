package com.example.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Product;
import com.example.repository.ProductRepository;
import com.example.service.ProductService;

@Service
public class ProductServiceImp implements ProductService{

	@Autowired
	private ProductRepository productRepository;
     
    @Override
    public List<Product> findAllProduct() {
    	System.out.println(productRepository.findAll());
        return (List<Product>) productRepository.findAll();
    }
    @Override
    public Optional<Product> findById(Integer id) {
        return productRepository.findById(id);
    }
    @Override
    public void save(Product product) {
        productRepository.save(product);
    }
    @Override
    public void remove(Integer id) {
        productRepository.deleteById(id);
    }
}
