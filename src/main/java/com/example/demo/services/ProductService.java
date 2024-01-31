package com.example.demo.services;

import com.example.demo.models.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Optional<Product> getById(Long id){
        return repository.findById(id);
    }

    public List<Product> getAll() {
        return repository.findAll();
    }

    public Optional<Product> update(Product product, Long id){
        Optional<Product> updatedProduct = repository.findById(id);
        if(updatedProduct.isPresent()){
            updatedProduct.get().setCode(product.getCode());
            updatedProduct.get().setTitle(product.getTitle());
            updatedProduct.get().setDescription(product.getDescription());
            updatedProduct.get().setPrice(product.getPrice());
            repository.save(updatedProduct.get());
        }
        return updatedProduct;
    }

    public Optional<Product> save (Product product){
        return Optional.of(repository.save(product));
    }

    public Optional<Product> delete(Long id){
        Optional<Product> product = repository.findById(id);
        product.ifPresent(value -> repository.delete(value));
        return product;
    }
}
