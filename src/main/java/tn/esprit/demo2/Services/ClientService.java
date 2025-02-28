package tn.esprit.demo2.Services;
import tn.esprit.demo2.entities.*;
import java.util.*;
public interface ClientService {
    Client createClient(Client client);
    List<Client> getAllClients();
    Optional<Client> getClientById(Long id);
    Client updateClient(Long id, Client clientDetails);
    void deleteClient(Long id);
}
