package com.github.tea_pack.sapphire.db_entities;

import java.util.List;

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
public class BroadcastDB {

    @Id
    public String name;
    public long channelID;
    public Integer ageRating;
    public String start;
    public String end;
    public Long duration;
    public String category;
    public List<String> genres;
}
