package com.example.bootforreact;

import com.example.bootforreact.model.Client;
import com.example.bootforreact.repo.ClientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientRepository clientRepository;

    public ClientController(final ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @GetMapping
    public List<Client> getClients(){
        return clientRepository.findAll();
    }

    @GetMapping("/{id}")
    public Client getClient(@PathVariable Long id){
        return clientRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) throws URISyntaxException {
        Client newClient = clientRepository.save(client);
        //System.out.println("new client: " + newClient);
        return ResponseEntity.created (
                new URI("/clients/" + newClient.getId()))
                .body(newClient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client changesForClient){
        Client clientToUpdate = clientRepository.findById(id).orElseThrow(RuntimeException::new);
        //System.out.println("******************************************************************** updating: " + clientToUpdate);
        clientToUpdate.setName(changesForClient.getName());
        clientToUpdate.setEmail(changesForClient.getEmail());
        clientRepository.save(clientToUpdate);
        return ResponseEntity.ok(clientToUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteClient(@PathVariable Long id){
        clientRepository.findById(id).orElseThrow(RuntimeException::new);
        clientRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
