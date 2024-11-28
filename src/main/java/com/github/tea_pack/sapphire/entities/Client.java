package com.github.tea_pack.sapphire.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    private long ID;
    private String address;
    private Gender gender;
    private Integer ageMin;
    private Integer ageMax;
}
