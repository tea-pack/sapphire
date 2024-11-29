package com.github.tea_pack.sapphire.dtos;

import com.github.tea_pack.sapphire.entities.Gender;

import lombok.Data;

@Data
public class ClientDTO {

    private long clientId;
    private String address;
    private Gender gender;
    private Integer ageMin;
    private Integer ageMax;
}
