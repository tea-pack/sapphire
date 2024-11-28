package com.github.tea_pack.sapphire.rest_api.sevice;

import java.util.List;

import com.github.tea_pack.sapphire.entities.Client;

public interface ClientService {

    void creat(Client client);

    List<Client> readAll();

    Client read(int id);

    boolean update(Client client, int id);

    boolean delete(int id);
}
