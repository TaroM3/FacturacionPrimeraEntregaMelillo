package com.example.demo.controllers;

import com.example.demo.dto.ClientDTO;
import com.example.demo.models.Client;
import com.example.demo.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<Client> getClients() {
        return clientService.getAll();
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getClientById (@PathVariable(name = "id") Long id){
        Optional<Client> client = clientService.getById(id);
        if(client.isPresent()){
            return ResponseEntity.ok(new ClientDTO(client.get().getName(), client.get().getSurname(), client.get().getBirthday()));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> saveClient(@RequestBody Client client){

            Optional<Client> createdClient = clientService.save(client);
            if (createdClient.isPresent()) {
                return ResponseEntity.created(URI.create("")).body(createdClient);
            }else {
                return ResponseEntity.internalServerError().body("Client has not created. . . ");
            }
    }

    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> updateClient(@PathVariable(name = "id") Long id, @RequestBody Client client){

            Optional<Client> updatedClient = clientService.update(client, id);
            if (updatedClient.isPresent()) {
                return ResponseEntity.ok(updatedClient);
            }else {
                return ResponseEntity.internalServerError().body("Client has not updated. . . ");
            }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){

            Optional<Client> deletedClient = clientService.delete(id);
            if (deletedClient.isPresent()){
                return ResponseEntity.ok(deletedClient);
            }else {
                return ResponseEntity.internalServerError().body("Client has not deleted. . . ");
            }
    }
}