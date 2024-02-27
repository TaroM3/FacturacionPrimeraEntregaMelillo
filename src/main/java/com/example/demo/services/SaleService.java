package com.example.demo.services;

import com.example.demo.models.Sale;
import com.example.demo.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    @Autowired
    private SaleRepository repository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProductService productService;


    public ResponseEntity<?> getById(Long id) {
        Optional<Sale> sale = repository.findById(id);
        if (sale.isPresent()){
            return ResponseEntity.ok(sale);
        }else {
            return ResponseEntity.internalServerError().body("Sale has not found. . . ");
        }
    }

    public ResponseEntity<?> getAll(){
        try{
            List<Sale> saleList = repository.findAll();
            return ResponseEntity.ok(saleList);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error. . . ");
        }
    }

    public ResponseEntity<?> update(Sale sale, Long id){
        Optional<Sale> updatedSale = repository.findById(id);
        if (updatedSale.isPresent()){
            updatedSale.get().setClientId(sale.getClientId());
            updatedSale.get().setCreatedAt(sale.getCreatedAt());
            updatedSale.get().setTotal(sale.getTotal());
            repository.save(updatedSale.get());
            return ResponseEntity.ok(updatedSale);
        }else {
            return ResponseEntity.internalServerError().body("Sale has not found. . . ");
        }
    }
    public ResponseEntity<?> save(Sale sale) {
        try{
            repository.save(sale);
         return ResponseEntity.ok("Sale has been saved. . . ");
        }catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error. . . ");
        }
    }

    public ResponseEntity<?> delete(Long id){
        Optional<Sale> sale = repository.findById(id);
        if (sale.isPresent()){
            repository.delete(sale.get());
            return ResponseEntity.ok("Sale deleted. . . ");
        }else {
            return ResponseEntity.internalServerError().body("Sale has not deleted. . . ");
        }
    }
}
