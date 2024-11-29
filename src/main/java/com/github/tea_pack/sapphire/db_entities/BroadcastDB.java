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
    // private Long broadcastId;
    private String name;
    private long channelId;
    private Integer ageRating;
    private String start;
    private String end;
    private Long duration;
    private String category;
    private List<String> genres;
}
