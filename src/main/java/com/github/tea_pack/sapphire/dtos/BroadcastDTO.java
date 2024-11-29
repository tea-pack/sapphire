package com.github.tea_pack.sapphire.dtos;

import java.time.Duration;
import java.time.LocalDateTime;

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
    public LocalDateTime start;
    public LocalDateTime end;
    public Duration duration;
}
