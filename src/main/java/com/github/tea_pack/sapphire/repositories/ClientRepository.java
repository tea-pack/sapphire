package com.github.tea_pack.sapphire.repositories;

import com.github.tea_pack.sapphire.entities.Client;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {

}