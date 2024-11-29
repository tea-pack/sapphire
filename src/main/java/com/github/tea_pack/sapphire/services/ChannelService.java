package com.github.tea_pack.sapphire.services;

import java.util.List;

import com.github.tea_pack.sapphire.db_entities.ChannelDB;
import com.github.tea_pack.sapphire.dtos.ChannelDTO;
import com.github.tea_pack.sapphire.repositories.ChannelRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ChannelService {

    private final ChannelRepository channelRepository;

    public ChannelDB create(ChannelDTO dto) {
        return channelRepository.save(ChannelDB.builder()
                .channelId(dto.getID())
                .pack(dto.getPack())
                .build());
    }

    public List<ChannelDB> readAll() {
        return channelRepository.findAll();
    }

    public ChannelDB update(ChannelDB channel) {
        return channelRepository.save(channel);
    }

    public void delete(Long channelId) {
        channelRepository.deleteById(channelId);
    }
}
