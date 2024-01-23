package com.example.demo.controllers;

import com.example.demo.models.Client;
import com.example.demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping
    public String index() {
      return "Online";
    }

    @GetMapping("clients")
    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    @PostMapping("add")
    public String post(@RequestBody Client client){
        clientRepository.save(client);
        return "New Client saved";
    }

    @PutMapping("clients/{id}")
    public String update(@PathVariable Long id, @RequestBody Client client){
        Client updatedClient = clientRepository.findById(id).get();
        updatedClient.setName(client.getName());
        updatedClient.setEmail(client.getEmail());
        clientRepository.save(updatedClient);
        return "Client modified";
    }

    @DeleteMapping("clients/{id}")
    public String delete(@PathVariable Long id){
        Client deletedClient = clientRepository.findById(id).get();
        clientRepository.delete(deletedClient);
        return "Client deleted";
    }
}
