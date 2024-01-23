package com.example.demo.controllers;

import com.example.demo.models.Sale;
import com.example.demo.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SaleController {

    @Autowired
    private SaleRepository saleRepository;

    @GetMapping("sales")
    public List<Sale> getSales(){
        return saleRepository.findAll();
    }

    @PostMapping("sales")
    public String post(@RequestBody Sale sale){
        saleRepository.save(sale);
        return "New sale added";
    }

    @PutMapping("sales/{id}")
    public String update(@PathVariable Long id, @RequestBody Sale sale){
        Sale updatedSale = saleRepository.findById(id).get();
        updatedSale.setClientId(sale.getClientId());
        updatedSale.setCreatedAt(sale.getCreatedAt());
        updatedSale.setTotal(sale.getTotal());
        return "Sale modified";
    }

    @DeleteMapping("sales/{id}")
    public String delete(@PathVariable Long id){
        Sale deletedSale = saleRepository.findById(id).get();
        saleRepository.delete(deletedSale);
        return "Sale deleted";
    }
}
