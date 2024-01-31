package com.example.demo.services;

import com.example.demo.models.Client;
import com.example.demo.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository repository;

    public Optional<Client> getById(Long id) {
        return repository.findById(id);
    }
    
    public List<Client> getAll(){
        return repository.findAll();
    }

    public Optional<Client> update(Client client, Long id){
        Optional<Client> updatedClient = repository.findById(id);
        if(updatedClient.isPresent()) {
            updatedClient.get().setName(client.getName());
            updatedClient.get().setSurname(client.getSurname());
            updatedClient.get().setBirthday(client.getBirthday());
            repository.save(updatedClient.get());
        }
        return updatedClient;

    }
    public Optional<Client> save(Client client){
        return Optional.of(repository.save(client));
    }

    public Optional<Client> delete(Long id){
        Optional<Client> client = repository.findById(id);
        client.ifPresent(value -> repository.delete(value));
        return client;
    }
}
