package com.github.tea_pack.sapphire.controllers;

import java.util.List;

import com.github.tea_pack.sapphire.dtos.ClientDTO;
import com.github.tea_pack.sapphire.entities.Client;
import com.github.tea_pack.sapphire.services.ClientService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<Client> create(@RequestBody ClientDTO dto) {
        return new ResponseEntity<>(clientService.create(dto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Client>> readAll() {
        return new ResponseEntity<>(clientService.readAll(), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Client> update(@RequestBody Client client) {
        return new ResponseEntity<>(clientService.update(client), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        clientService.delete(id);
        return HttpStatus.OK;
    }
}
