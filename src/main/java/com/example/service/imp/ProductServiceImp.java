package com.example.service.imp;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    	List<Product> product = (List<Product>) productRepository.findAll();
    	if(product.size() > 0) {
    		return product;
    	}else {
    		return new ArrayList<Product>();
    		
    	}
         
    }
    @Override
    public Optional<Product> findById(Integer id){
    	
    	Optional<Product> product = productRepository.findById(id);
    	if(product.isPresent()) {
    		return product;
    	}

    	return product;
    }
    @Override
    public void save(Product product) {
        productRepository.save(product);
    }
    @Override
    public void remove(Integer id) {
        productRepository.deleteById(id);
    }
    
    @Override
    public List<Product> getAllEmployeesSortPage(Integer pageNo, Integer pageSize, String sortBy)
    {
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());
        
        Page<Product> pagedResult = productRepository.findAll(paging);
        System.out.println(pagedResult);
        if(pagedResult.hasContent()) {
            return pagedResult.getContent();
        } else {
            return new ArrayList<Product>();
        }
    }
    
//    @Override
//    public List<Product> getAllEmployeesSort(HashMap<String, String> sortBy){
//    	
//    	Sort sort = Sort.by(sortBy);
//    	return (List<Product>) productRepository.findAll(sort);
//          
//    }
    @Override
    
    public List<Product> getAllEmployeesMutiSort(String sortBy1, String sortBy2){
    	
    	Sort sort1 = Sort.by(sortBy1);
    	Sort sort2 = Sort.by(sortBy2);
    	Sort groupSort = sort1.and(sort2);
    	return (List<Product>) productRepository.findAll(groupSort);
          
    }
 
}
