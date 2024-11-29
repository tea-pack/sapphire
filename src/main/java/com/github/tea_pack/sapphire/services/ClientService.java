package com.github.tea_pack.sapphire.services;

import java.util.ArrayList;
import java.util.List;

import com.github.tea_pack.sapphire.dtos.ClientDTO;
import com.github.tea_pack.sapphire.entities.Client;
import com.github.tea_pack.sapphire.entities.Gender;
import com.github.tea_pack.sapphire.repositories.ClientRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public Client create(ClientDTO dto) {
        return clientRepository.save(Client.builder()
                .clientId(dto.getClientId())
                .address(dto.getAddress())
                .gender(dto.getGender())
                .ageMin(dto.getAgeMin())
                .ageMax(dto.getAgeMax())
                .build());
    }

    public List<Client> readAll() {
        return clientRepository.findAll();
    }

    public Client readById(Long clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("No client with id=" + clientId));
    }

    public List<Client> readByGender(String gender) {

        List<Client> clients = new ArrayList<>();
        for (Client c : readAll()) {
            if (Gender.of(gender) == c.getGender()) {
                clients.add(c);
            }
        }
        return clients;
    }

    public Client update(Client client) {
        return clientRepository.save(client);
    }

    public void delete(Long id) {
        clientRepository.deleteById(id);
    }
}
