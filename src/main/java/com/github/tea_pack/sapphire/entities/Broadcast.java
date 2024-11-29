package com.github.tea_pack.sapphire.entities;

import com.github.tea_pack.sapphire.parsers.ViewParser;
import com.github.tea_pack.sapphire.statistics.ChannelPopularity;
import com.github.tea_pack.sapphire.utility.FMT;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

import static com.github.tea_pack.sapphire.utility.FMT.DATE_TIME_FORMAT;

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

    @Override
    public String toString() {
        return name +
                "\nRating: " + ageRating +
                "\n" + start.format(DATE_TIME_FORMAT) +
                " -- " + end.format(DATE_TIME_FORMAT) +
                ", duration: " + FMT.formatDuration(duration) +
                "\ncategory: " + category +
                ", genres: " + genres +
                "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Broadcast broadcast)) return false;
		return channelID == broadcast.channelID && Objects.equals(name, broadcast.name)
                && Objects.equals(start, broadcast.start) && Objects.equals(end,broadcast.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, channelID, start, end);
    }
}
