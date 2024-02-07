package com.example.demo.services;

import com.example.demo.models.Product;
import com.example.demo.models.Sale;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public ResponseEntity<?> getById(Long id){
        Optional<Product> product = repository.findById(id);
        if (product.isPresent()) {
            return ResponseEntity.ok(product);
        }else {
            return ResponseEntity.internalServerError().body("Product has not found. . . ");
        }
    }

    public ResponseEntity<?> getAll() {
        try{
            List<Product> productListList = repository.findAll();
            return ResponseEntity.ok(productListList);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error. . . ");
        }
    }

    public ResponseEntity<?> update(Product product, Long id){
        Optional<Product> updatedProduct = repository.findById(id);
        if(updatedProduct.isPresent()){
            updatedProduct.get().setCode(product.getCode());
            updatedProduct.get().setTitle(product.getTitle());
            updatedProduct.get().setDescription(product.getDescription());
            updatedProduct.get().setPrice(product.getPrice());
            repository.save(updatedProduct.get());
            return ResponseEntity.ok(updatedProduct);
        }else {
         return ResponseEntity.internalServerError().body("Product has not updated. . . ");
        }
    }

    public ResponseEntity<?> save(Product product){
        try{
            repository.save(product);
            return ResponseEntity.ok("Product has been saved. . . ");
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Product has not been saved. . . ");
        }
    }

    public ResponseEntity<?> delete(Long id){
        Optional<Product> product = repository.findById(id);
        if (product.isPresent()){
            repository.delete(product.get());
            return ResponseEntity.ok(product);
        }else {
            return ResponseEntity.internalServerError().body("Product has not been deleted. . . ");
        }
    }
}
