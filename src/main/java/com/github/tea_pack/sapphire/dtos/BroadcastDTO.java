package com.github.tea_pack.sapphire.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BroadcastDTO {

    public String name;
    public int channelID;
    public Integer ageRating;
    public String start;
    public String end;
    public Long duration;
    public String category;
    public List<String> genres;
}
