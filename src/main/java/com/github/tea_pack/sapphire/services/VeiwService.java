package com.github.tea_pack.sapphire.services;

import java.util.List;

import com.github.tea_pack.sapphire.db_entities.ViewDB;
import com.github.tea_pack.sapphire.dtos.ViewDTO;
import com.github.tea_pack.sapphire.repositories.ViewRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VeiwService {

    private final ViewRepository clientRepository;

    public ViewDB create(ViewDTO dto) {
        return clientRepository.save(ViewDB.builder()
                .clientID(dto.getClientID())
                .deviceID(dto.getDeviceID())
                .start(dto.getStart())
                .end(dto.getEnd())
                .duration(dto.getDuration())
                // .broadcast(dto.getBroadcast())
                .broadcastId(dto.getBroadcastId())
                .build());
    }

    public List<ViewDB> readAll() {
        return clientRepository.findAll();
    }

    public ViewDB readById(Long clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("No client with id=" + clientId));
    }

    public ViewDB update(ViewDB client) {
        return clientRepository.save(client);
    }

    public void delete(Long id) {
        clientRepository.deleteById(id);
    }
}
