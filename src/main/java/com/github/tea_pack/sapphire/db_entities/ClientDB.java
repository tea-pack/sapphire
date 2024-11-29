package com.github.tea_pack.sapphire.db_entities;

import com.github.tea_pack.sapphire.entities.Gender;

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
public class ClientDB {

    @Id
    private long clientId;
    private String address;
    private Gender gender;
    private Integer ageMin;
    private Integer ageMax;
}
