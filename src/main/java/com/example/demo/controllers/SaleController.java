package com.example.demo.controllers;

import com.example.demo.models.Sale;
import com.example.demo.repository.SaleRepository;
import com.example.demo.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @GetMapping
    public ResponseEntity<?> getSales(){
        return saleService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(Long id){
        return saleService.getById(id);
    }

    @PostMapping("sales")
    public ResponseEntity<?> post(@RequestBody Sale sale){
        return saleService.save(sale);
    }

    @PutMapping("sales/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Sale sale){
        return saleService.update(sale, id);
    }

    @DeleteMapping("sales/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        return saleService.delete(id);
    }
}
