package com.github.tea_pack.sapphire.services;

import java.util.List;

import com.github.tea_pack.sapphire.dtos.BroadcastDTO;
import com.github.tea_pack.sapphire.entities.Broadcast;
import com.github.tea_pack.sapphire.parsers.ViewParser;
import com.github.tea_pack.sapphire.repositories.BroadcastRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BroadcastService {

    private final BroadcastRepository clientRepository;

    public Broadcast create(BroadcastDTO dto) {
        return clientRepository.save(Broadcast.builder()
                .channelID(dto.getChannelID())
                .name(dto.getName())
                .channelID(dto.getChannelID())
                .ageRating(dto.getAgeRating())
                .start(dto.getStart().format(ViewParser.DATE_TIME_FORMAT))
                .end(dto.getEnd().format(ViewParser.DATE_TIME_FORMAT))
                .category(dto.getCategory())
                .genres(dto.getGenres())
                .build());
    }

    public List<Broadcast> readAll() {
        return clientRepository.findAll();
    }

    public Broadcast readById(Long clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("No broadcast with id=" + clientId));
    }

    public Broadcast update(Broadcast broadcast) {
        return clientRepository.save(broadcast);
    }

    public void delete(Long id) {
        clientRepository.deleteById(id);
    }
}
