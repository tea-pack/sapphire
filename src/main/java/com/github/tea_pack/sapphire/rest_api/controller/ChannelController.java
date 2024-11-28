package com.github.tea_pack.sapphire.rest_api.controller;

import java.util.List;

import com.github.tea_pack.sapphire.entities.Channel;
import com.github.tea_pack.sapphire.rest_api.sevice.ChannelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChannelController {

    private final ChannelService channelService;

    @Autowired
    public ChannelController(ChannelService channelService) {
        this.channelService = channelService;
    }

    @PostMapping(value = "/channels")
    public ResponseEntity<?> create(@RequestBody Channel channel) {
        channelService.creat(channel);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/channels")
    public ResponseEntity<List<Channel>> read() {
        final List<Channel> clients = channelService.readAll();

        return clients != null && !clients.isEmpty()
                ? new ResponseEntity<>(clients, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/channels/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Channel channel) {
        final boolean updated = channelService.update(channel, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/channels/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = channelService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
