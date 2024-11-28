package com.github.tea_pack.sapphire.rest_api.controller;

import java.util.List;

import com.github.tea_pack.sapphire.entities.Broadcast;
import com.github.tea_pack.sapphire.rest_api.sevice.BroadcastService;

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
public class BroadcastController {

    private final BroadcastService broadcastService;

    @Autowired
    public BroadcastController(BroadcastService broadcastService) {
        this.broadcastService = broadcastService;
    }

    @PostMapping(value = "/broadcasts")
    public ResponseEntity<?> create(@RequestBody Broadcast broadcast) {
        broadcastService.creat(broadcast);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/broadcasts")
    public ResponseEntity<List<Broadcast>> read() {
        final List<Broadcast> clients = broadcastService.readAll();

        return clients != null && !clients.isEmpty()
                ? new ResponseEntity<>(clients, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/broadcasts/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Broadcast broadcast) {
        final boolean updated = broadcastService.update(broadcast, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/broadcasts/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = broadcastService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
