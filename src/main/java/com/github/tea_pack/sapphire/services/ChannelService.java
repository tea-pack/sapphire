package com.github.tea_pack.sapphire.services;

import java.util.List;

import com.github.tea_pack.sapphire.dtos.ChannelDTO;
import com.github.tea_pack.sapphire.entities.Channel;
import com.github.tea_pack.sapphire.repositories.ChannelRepository;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ChannelService {

    private final ChannelRepository channelRepository;

    public Channel create(ChannelDTO dto) {
        return channelRepository.save(Channel.builder()
                .channelId(dto.getID())
                .pack(dto.getPack())
                .build());
    }

    public List<Channel> readAll() {
        return channelRepository.findAll();
    }

    public Channel update(Channel channel) {
        return channelRepository.save(channel);
    }

    public void delete(Long channelId) {
        channelRepository.deleteById(channelId);
    }
}
