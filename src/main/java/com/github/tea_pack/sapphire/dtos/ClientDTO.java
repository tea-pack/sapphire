package com.github.tea_pack.sapphire.dtos;

import com.github.tea_pack.sapphire.entities.Gender;
import com.github.tea_pack.sapphire.entities.Range;

import lombok.Data;

@Data
public class ClientDTO {

    private String address;
    private Gender gender;
    private Range age;
}
