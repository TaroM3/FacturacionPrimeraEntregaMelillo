package com.example.demo.services;

import com.example.demo.dto.ClientDTO;
import com.example.demo.models.Client;
import com.example.demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;

    public ResponseEntity<?> getById(Long id) {
        Optional<Client> client = repository.findById(id);
        if(client.isPresent()){
            return ResponseEntity.ok(new ClientDTO(client.get().getName(),client.get().getSurname(), client.get().getBirthday()));
        }else {
            return ResponseEntity.internalServerError().body("Client has not found. . . ");
        }
    }
    
    public ResponseEntity<?> getAll(){
        try{
            List<Client> clients = repository.findAll();
            return ResponseEntity.ok(clients);
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Error. . . ");
        }
    }

    public ResponseEntity<?> update(Client client, Long id){
        Optional<Client> updatedClient = repository.findById(id);
        if(updatedClient.isPresent()) {
            updatedClient.get().setName(client.getName());
            updatedClient.get().setSurname(client.getSurname());
            updatedClient.get().setBirthday(client.getBirthday());
            repository.save(updatedClient.get());
            return ResponseEntity.ok("Client has been updated. . . ");
        }else {
            return ResponseEntity.internalServerError().body("Client has not updated. . . ");
        }


    }
    public ResponseEntity<?> save(Client client){
        try{
            repository.save(client);
            return ResponseEntity.ok("Client has created. . . ");
        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Client has not created. . . ");
        }
    }

    public ResponseEntity<?> delete(Long id){
        Optional<Client> client = repository.findById(id);
        if (client.isPresent()){
            repository.delete(client.get());
            return ResponseEntity.ok("Client has been deleted. . . ");
        }else {
            return ResponseEntity.internalServerError().body("Client has not deleted. . . ");
        }
    }
}
