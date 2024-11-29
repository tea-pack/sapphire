package com.github.tea_pack.sapphire.parsers;

import java.util.ArrayList;
import java.util.List;

import com.github.tea_pack.sapphire.dtos.ClientDTO;
import com.github.tea_pack.sapphire.entities.Gender;
import com.github.tea_pack.sapphire.entities.Range;

public class ClientParser {
    public static ClientDTO parse(String[] values) {
        int ID = Integer.parseInt(values[0].substring(1));
        String address = values[1];
        Gender gender = Gender.of(values[2]);
        Range age = Range.from(values[3]);
        // return new ClientDTO(ID, address, gender, age.start, age.end);
        ClientDTO client = new ClientDTO();
        client.setClientId(ID);
        client.setAddress(address);
        client.setGender(gender);
        client.setAgeMin(age.start);
        client.setAgeMax(age.end);
        return client;
    }

    public static List<ClientDTO> parse(List<String[]> values) {
        List<ClientDTO> clients = new ArrayList<>();
        for (String[] val : values) {
            clients.add(parse(val));
        }
        return clients;
    }
}
