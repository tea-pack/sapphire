package com.github.tea_pack.sapphire.services;

import java.util.ArrayList;
import java.util.List;

import com.github.tea_pack.sapphire.db_entities.ClientDB;
import com.github.tea_pack.sapphire.dtos.ClientDTO;
import com.github.tea_pack.sapphire.entities.Gender;
import com.github.tea_pack.sapphire.repositories.ClientRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientDB create(ClientDTO dto) {
        return clientRepository.save(ClientDB.builder()
                .clientId(dto.getClientId())
                .address(dto.getAddress())
                .gender(dto.getGender())
                .ageMin(dto.getAgeMin())
                .ageMax(dto.getAgeMax())
                .build());
    }

    public List<ClientDB> readAll() {
        return clientRepository.findAll();
    }

    public ClientDB readById(Long clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("No client with id=" + clientId));
    }

    public List<ClientDB> readByGender(String gender) {

        List<ClientDB> clients = new ArrayList<>();
        for (ClientDB c : readAll()) {
            if (Gender.of(gender) == c.getGender()) {
                clients.add(c);
            }
        }
        return clients;
    }

    public ClientDB update(ClientDB client) {
        return clientRepository.save(client);
    }

    public void delete(Long id) {
        clientRepository.deleteById(id);
    }
}
