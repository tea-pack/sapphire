package com.github.tea_pack.sapphire.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BroadcastDTO {

    private String name;
    private int channelID;
    private Integer ageRating;
    private String start;
    private String end;
    private Long duration;
    private String category;
    private List<String> genres;
}
