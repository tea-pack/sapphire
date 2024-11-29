package com.github.tea_pack.sapphire.controllers;

import java.util.List;

import com.github.tea_pack.sapphire.dtos.ChannelDTO;
import com.github.tea_pack.sapphire.entities.Channel;
import com.github.tea_pack.sapphire.services.ChannelService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/channels")
@AllArgsConstructor
public class ChannelController {

    private final ChannelService channelService;

    @PostMapping
    public ResponseEntity<Channel> create(@RequestBody ChannelDTO dto) {
        return new ResponseEntity<>(channelService.create(dto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Channel>> readAll() {
        return new ResponseEntity<>(channelService.readAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Channel> update(@RequestBody Channel channel) {
        return new ResponseEntity<>(channelService.update(channel), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        channelService.delete(id);
        return HttpStatus.OK;
    }
}
