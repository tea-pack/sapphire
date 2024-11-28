package com.github.tea_pack.sapphire.services;

import java.util.List;

import com.github.tea_pack.sapphire.dtos.ClientDTO;
import com.github.tea_pack.sapphire.entities.Client;
import com.github.tea_pack.sapphire.repositories.ClientRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public Client create(ClientDTO dto) {
        return clientRepository.save(Client.builder()
                .address(dto.getAddress())
                .gender(dto.getGender())
                .age(dto.getAge())
                .build());
    }

    public List<Client> readAll() {
        return clientRepository.findAll();
    }

    public Client update(Client client) {
        return clientRepository.save(client);
    }

    public void delete(Long id) {
        clientRepository.deleteById(id);
    }
}
