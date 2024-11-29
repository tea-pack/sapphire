package com.github.tea_pack.sapphire.parsers;

import java.util.ArrayList;
import java.util.List;

import com.github.tea_pack.sapphire.entities.Client;
import com.github.tea_pack.sapphire.entities.Gender;

public class ClientParser {
    public static Client parse(String[] values) {
        int ID = Integer.parseInt(values[0].substring(1));
        String address = values[1];
        Gender gender = Gender.of(values[2]);
        int age = parseAge(values[3]);
        return new Client(ID, address, gender, age);
    }

    public static List<Client> parse(List<String[]> values) {
        List<Client> clients = new ArrayList<>();
        for (String[] val : values) {
            clients.add(parse(val));
        }
        return clients;
    }

    private static int parseAge(String from) {
        String[] split = from.split("-");
        int left = Integer.parseInt(split[0]);
        int right = Integer.parseInt(split[1]);
        return (left+right)/2;
    }
}
