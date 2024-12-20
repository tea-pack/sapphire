package com.github.tea_pack.sapphire.controllers;

import java.util.List;

import com.github.tea_pack.sapphire.db_entities.ClientDB;
import com.github.tea_pack.sapphire.dtos.ClientDTO;
import com.github.tea_pack.sapphire.services.ClientService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/clients")
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<ClientDB> create(@RequestBody ClientDTO dto) {
        return new ResponseEntity<>(clientService.create(dto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ClientDB>> readAll() {
        return new ResponseEntity<>(clientService.readAll(), HttpStatus.OK);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<ClientDB> readById(@PathVariable long clientId) {
        return new ResponseEntity<>(clientService.readById(clientId), HttpStatus.OK);
    }

    @GetMapping("/statistics")
    public ResponseEntity<List<ClientDB>> readByGender(@RequestParam(name = "gender") String gender) {
        return new ResponseEntity<>(clientService.readByGender(gender), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ClientDB> update(@RequestBody ClientDB client) {
        return new ResponseEntity<>(clientService.update(client), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        clientService.delete(id);
        return HttpStatus.OK;
    }
}
