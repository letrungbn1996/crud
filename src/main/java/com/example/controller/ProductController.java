package com.example.controller;

 
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.model.Product;
import com.example.repository.ProductRepository;
import com.example.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
 
	
	private static final Logger LOGGER = LogManager.getLogger(ProductController.class);

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	ResponseEntity<List<Product>> findAllProduct() {
		List<Product> products = productService.findAllProduct();
		if (products.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/productsSortPage", method = RequestMethod.GET)
	ResponseEntity<List<Product>> findAllProductSortPage(
			@RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) {
		List<Product> products = productService.getAllEmployeesSortPage(pageNo,pageSize,sortBy);
		 
		return new ResponseEntity<>(products,new HttpHeaders(), HttpStatus.OK);
	}
//	@RequestMapping(value = "/productsSort", method = RequestMethod.GET)
//	ResponseEntity<List<Product>> findAllProductSort(
//			@RequestParam(defaultValue = "id") HashMap<String, String> sortBy) {
//		List<Product> products = productService.getAllEmployeesSort(sortBy);
//		if (products.isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
//		return new ResponseEntity<>(products, HttpStatus.OK);
//	}
	
	
	@RequestMapping(value = "/productsSortMuti", method = RequestMethod.GET)
	ResponseEntity<List<Product>> findAllProductSort(
			@RequestParam(defaultValue = "id") String sortBy1,
			@RequestParam(defaultValue = "id") String sortBy2) {
		List<Product> products = productService.getAllEmployeesMutiSort(sortBy1, sortBy2);
		if (products.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value = "/product/{id}",method = RequestMethod.GET,
            		produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Product> getProductById(@PathVariable("id") Integer id) {        
		Optional<Product> product = productService.findById(id);
        
        if (!product.isPresent()) {
        	LOGGER.error(product);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
       return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }

	@RequestMapping(value = "/product",method = RequestMethod.POST)
    ResponseEntity<Product> createProduct(
    		@RequestBody Product product,
            UriComponentsBuilder builder) {
        productService.save(product);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/products/{id}")
                .buildAndExpand(product.getId()).toUri());
       return new ResponseEntity<>(product, HttpStatus.CREATED);
    }
	
	@RequestMapping(value = "/product/{id}",method = RequestMethod.POST)
    ResponseEntity<Product> updateProduct(
            @PathVariable("id") Integer id,
            @RequestBody Product product) {
        Optional<Product> currentProduct = productService.findById(id);
        if (!currentProduct.isPresent()) {
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        currentProduct.get().setName(product.getName());
        currentProduct.get().setPrice(product.getPrice());
        currentProduct.get().setDescription(product.getDescription());
        productService.save(currentProduct.get());
        return new ResponseEntity<>(currentProduct.get(), HttpStatus.OK);
    }
	
	@RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE)
	public void deleteProduct(@PathVariable("id") Integer id) { 
		productService.remove(id);
	}
}
