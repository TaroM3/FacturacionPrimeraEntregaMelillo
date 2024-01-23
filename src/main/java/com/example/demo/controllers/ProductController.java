package com.example.demo.controllers;

import com.example.demo.models.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("products")
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @PostMapping("products")
    public String post(@RequestBody Product product){
        productRepository.save(product);
        return "New product added";
    }

    @PutMapping("products/{id}")
    public String update(@PathVariable Long id, @RequestBody Product product){
        Product updatedProduct = productRepository.findById(id).get();
        updatedProduct.setTitle(product.getTitle());
        updatedProduct.setStock(product.getStock());
        updatedProduct.setPrice(product.getPrice());
        updatedProduct.setDescription(product.getDescription());
        updatedProduct.setCode(product.getCode());
        return "Product modified";
    }

    @DeleteMapping("products/{id}")
    public String delete(@PathVariable Long id){
        Product deletedProduct = productRepository.findById(id).get();
        productRepository.delete(deletedProduct);
        return "Product deleted";
    }

}
