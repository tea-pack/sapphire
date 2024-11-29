package com.github.tea_pack.sapphire.controllers;

import java.util.List;

import com.github.tea_pack.sapphire.db_entities.BroadcastDB;
import com.github.tea_pack.sapphire.dtos.BroadcastDTO;
import com.github.tea_pack.sapphire.services.BroadcastService;

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
@RequestMapping("/broadcasts")
@AllArgsConstructor
public class BroadcastController {

    private final BroadcastService broadcastService;

    @PostMapping
    public ResponseEntity<BroadcastDB> create(@RequestBody BroadcastDTO dto) {
        return new ResponseEntity<>(broadcastService.create(dto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BroadcastDB>> readAll() {
        return new ResponseEntity<>(broadcastService.readAll(), HttpStatus.OK);
    }

    @GetMapping("/{broadastId}")
    public ResponseEntity<BroadcastDB> readById(@PathVariable long broadcastId) {
        return new ResponseEntity<>(broadcastService.readById(broadcastId), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<BroadcastDB> update(@RequestBody BroadcastDB broadcast) {
        return new ResponseEntity<>(broadcastService.update(broadcast), HttpStatus.OK);
    }

    @DeleteMapping("/{broadcastId}")
    public HttpStatus delete(@PathVariable Long broadcastId) {
        broadcastService.delete(broadcastId);
        return HttpStatus.OK;
    }
}
