package com.github.tea_pack.sapphire.rest_api.sevice;

import java.util.List;

import com.github.tea_pack.sapphire.entities.Channel;

public interface ChannelService {

    void creat(Channel client);

    List<Channel> readAll();

    Channel read(int id);

    boolean update(Channel client, int id);

    boolean delete(int id);
}
