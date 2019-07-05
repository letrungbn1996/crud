package com.example.controller;

 
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.model.Products;
import com.example.repository.ProductRepository;
import com.example.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired 
	private ProductRepository productRepository;
	
	
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public ResponseEntity<List<Products>> findAllProduct(){
		List<Products> products =  (List<Products>) productRepository.findAll();
		if(products.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
		}
		return new ResponseEntity<>(products, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
	public ResponseEntity<Products> getProductById(@PathVariable("id") Integer id){
		
		Optional<Products> products  = productRepository.findById(id);
		if(!products.isPresent()) {
			
			return new ResponseEntity<>(products.get(), HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(products.get(), HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/product/{id}", method =  RequestMethod.POST)
	public ResponseEntity<Products> updateProductById(@PathVariable("id") Integer id, @RequestBody Products product){
		
		Optional<Products> productCurrent = productRepository.findById(id);
		if(!productCurrent.isPresent()) {
			
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
		productCurrent.get().setName(product.getName());
		productCurrent.get().setDescription(product.getDescription());
		productCurrent.get().setPrice(product.getPrice());
		productRepository.save(productCurrent.get());
		return new ResponseEntity<>(productCurrent.get(), HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/product", method = RequestMethod.POST)
	public ResponseEntity<Products> addProduct(@RequestBody Products product){
		productRepository.save(product);
		return new ResponseEntity<>(product, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE)
	public void deleteProduct(@PathVariable("id") Integer id) {
		productRepository.deleteById(id);
	
	}
	
	
	
	
	
	
	
//	@RequestMapping(value = "/products", method = RequestMethod.GET)
//	public ResponseEntity<List<Products>> findAllProduct() {
//		List<Products> products = productService.findAllProduct();
//		if (products.isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
//		return new ResponseEntity<>(products, HttpStatus.OK);
//	}
	 
//	@RequestMapping(value = "/products", method = RequestMethod.GET)
//	@ResponseBody
//	public List<Products> findAllProduct() {
//		return productService.findAllProduct();
//		 
//	}
	
//	@RequestMapping(value = "/products/{id}",method = RequestMethod.GET,
//            		produces = MediaType.APPLICATION_JSON_VALUE)
//   public ResponseEntity<Products> getProductById(@PathVariable("id") Integer id) {
//        Optional<Products> product = productService.findById(id);
//        System.out.println(product);
//        if (!product.isPresent()) {
//            return new ResponseEntity<>(product.get(),HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(product.get(), HttpStatus.OK);
//    }
//	
//	@RequestMapping(value = "/products/{id}",method = RequestMethod.GET,
//    				produces = MediaType.APPLICATION_JSON_VALUE)
//	@ResponseBody
//	public Optional<Products> getProductById(@PathVariable("id") Integer id) {
//		return productService.findById(id);
//		 
//	}
//	
//	
//	@RequestMapping(value = "/products",method = RequestMethod.POST)
//    public ResponseEntity<Products> createProduct(
//            @RequestBody Products product,
//            UriComponentsBuilder builder) {
//        productService.save(product);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(builder.path("/products/{id}")
//                .buildAndExpand(product.getId()).toUri());
//       return new ResponseEntity<>(product, HttpStatus.CREATED);
//    }
	
//	@RequestMapping(value = "/products",method = RequestMethod.POST)
//	@ResponseBody
//    public Products createProduct(
//            @RequestBody Products product,
//            UriComponentsBuilder builder) {
//    productService.save(product);
////    HttpHeaders headers = new HttpHeaders();
////    headers.setLocation(builder.path("/products/{id}")
////           .buildAndExpand(product.getId()).toUri());
//    return product;
//    }
	
//	@RequestMapping(value = "/products/{id}",method = RequestMethod.POST)
//    public ResponseEntity<Products> updateProduct(
//            @PathVariable("id") Integer id,
//            @RequestBody Products product) {
//        Optional<Products> currentProduct = productService.findById(id);
//        if (!currentProduct.isPresent()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        currentProduct.get().setName(product.getName());
//        currentProduct.get().setPrice(product.getPrice());
//        currentProduct.get().setDescription(product.getDescription());
//        productService.save(currentProduct.get());
//        return new ResponseEntity<>(currentProduct.get(), HttpStatus.OK);
//    }
	
//	@RequestMapping(value = "/products/{id}",method = RequestMethod.DELETE)
//    public ResponseEntity<Products> deleteProduct(@PathVariable("id") Integer id) {Optional<Products> product = productService.findById(id);
//        if (!product.isPresent()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        productService.remove(product.get());
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
////    }
//	
//	@RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
//	public void deleteProduct(@PathVariable("id") Integer id) { 
//		productService.remove(id);
//	}
}
