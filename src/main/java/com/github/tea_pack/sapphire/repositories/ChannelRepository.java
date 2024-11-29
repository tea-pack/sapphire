package com.github.tea_pack.sapphire.repositories;

import com.github.tea_pack.sapphire.db_entities.ChannelDB;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelRepository extends JpaRepository<ChannelDB, Long> {

}
