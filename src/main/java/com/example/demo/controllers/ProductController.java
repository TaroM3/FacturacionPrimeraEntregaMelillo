package com.example.demo.controllers;

import com.example.demo.models.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getProducts() {
        return productService.getAll();
    }

    @PostMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> post(@RequestBody Product product){
        Optional<Product> createdProduct = productService.save(product);
        if(createdProduct.isPresent()){
            return ResponseEntity.created(URI.create("")).body(createdProduct);
        }else {
            return ResponseEntity.internalServerError().body("Product has not created. . . ");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Product product){
//        Product updatedProduct = productService.findById(id).get();
//        updatedProduct.setTitle(product.getTitle());
//        updatedProduct.setStock(product.getStock());
//        updatedProduct.setPrice(product.getPrice());
//        updatedProduct.setDescription(product.getDescription());
//        updatedProduct.setCode(product.getCode());
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){

        return ResponseEntity.ok("Product deleted. . . ");
    }

}
