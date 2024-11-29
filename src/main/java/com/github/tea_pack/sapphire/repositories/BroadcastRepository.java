package com.github.tea_pack.sapphire.repositories;

import com.github.tea_pack.sapphire.db_entities.BroadcastDB;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BroadcastRepository extends JpaRepository<BroadcastDB, Long> {

}
