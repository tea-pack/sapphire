package com.github.tea_pack.sapphire.controllers;

import java.util.List;

import com.github.tea_pack.sapphire.db_entities.ViewDB;
import com.github.tea_pack.sapphire.dtos.ViewDTO;
import com.github.tea_pack.sapphire.services.VeiwService;

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
@RequestMapping("/views")
@AllArgsConstructor
public class ViewController {

    private final VeiwService viewService;

    @PostMapping
    public ResponseEntity<ViewDB> create(@RequestBody ViewDTO dto) {
        return new ResponseEntity<>(viewService.create(dto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ViewDB>> readAll() {
        return new ResponseEntity<>(viewService.readAll(), HttpStatus.OK);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<ViewDB> readById(@PathVariable long clientId) {
        return new ResponseEntity<>(viewService.readById(clientId), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ViewDB> update(@RequestBody ViewDB client) {
        return new ResponseEntity<>(viewService.update(client), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Long id) {
        viewService.delete(id);
        return HttpStatus.OK;
    }
}
