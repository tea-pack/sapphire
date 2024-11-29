package com.github.tea_pack.sapphire.entities;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class Broadcast {
    public String name;
    public long channelID;
    public Integer ageRating;
    public LocalDateTime start;
    public LocalDateTime end;
    public Duration duration;
    public String category;
    public List<String> genres;

    public Broadcast(String name, long channelID, Integer ageRating, LocalDateTime start, LocalDateTime end, String category, List<String> genres) {
        this.name = name;
        this.channelID = channelID;
        this.ageRating = ageRating;
        this.start = start;
        this.end = end;
        this.duration = Duration.between(start, end);
        this.category = category;
        this.genres = genres;
    }
}
