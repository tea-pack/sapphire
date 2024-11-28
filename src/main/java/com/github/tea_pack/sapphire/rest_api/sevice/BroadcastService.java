package com.github.tea_pack.sapphire.rest_api.sevice;

import java.util.List;

import com.github.tea_pack.sapphire.entities.Broadcast;

public interface BroadcastService {

    void creat(Broadcast client);

    List<Broadcast> readAll();

    Broadcast read(int id);

    boolean update(Broadcast client, int id);

    boolean delete(int id);
}
