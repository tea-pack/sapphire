package com.github.tea_pack.sapphire.services;

import java.util.List;

import com.github.tea_pack.sapphire.db_entities.BroadcastDB;
import com.github.tea_pack.sapphire.dtos.BroadcastDTO;
import com.github.tea_pack.sapphire.repositories.BroadcastRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BroadcastService {

    private final BroadcastRepository broadcastRepository;

    public BroadcastDB create(BroadcastDTO dto) {
        return broadcastRepository.save(BroadcastDB.builder()
                .name(dto.getName())
                .channelId(dto.getChannelID())
                .ageRating(dto.getAgeRating())
                .start(dto.getStart())
                .end(dto.getEnd())
                .category(dto.getCategory())
                .genres(dto.getGenres())
                .build());
    }

    public List<BroadcastDB> readAll() {
        return broadcastRepository.findAll();
    }

    public BroadcastDB readById(Long broadcastId) {
        return broadcastRepository.findById(broadcastId)
                .orElseThrow(() -> new RuntimeException("No broadcast with id=" + broadcastId));
    }

    public BroadcastDB update(BroadcastDB broadcast) {
        return broadcastRepository.save(broadcast);
    }

    public void delete(Long id) {
        broadcastRepository.deleteById(id);
    }
}
