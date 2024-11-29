package com.github.tea_pack.sapphire.repositories;

import com.github.tea_pack.sapphire.db_entities.ClientDB;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<ClientDB, Long> {

}
