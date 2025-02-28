package tn.esprit.demo2.Services.imp;
import org.springframework.beans.factory.annotation.Autowired;

import tn.esprit.demo2.entities.*;
import tn.esprit.demo2.Services.ClientService;
import org.springframework.stereotype.Service;
import tn.esprit.demo2.repositories.ClientRepository;


import java.util.List;
import java.util.Optional;

@Service

public class CIientServiceImp implements ClientService {


    @Autowired
    private ClientRepository clientRepository;
    @Override
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> getClientById(Long id) {
        return Optional.empty();
    }

    @Override
    public Client updateClient(Long id, Client clientDetails) {
        return clientDetails;
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}
