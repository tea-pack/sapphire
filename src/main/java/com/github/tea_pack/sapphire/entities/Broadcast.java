package com.github.tea_pack.sapphire.entities;

import java.time.Duration;
import java.time.LocalDateTime;

public class Broadcast {
    public int entityID;
	public final String name;
	public final int channelID;
	public final Integer ageRating;
	public final LocalDateTime start;
	public final LocalDateTime end;
	public final Duration duration;

	public Broadcast(String name, int channelID, Integer ageRating, LocalDateTime start, LocalDateTime end) {
		this.name = name;
		this.channelID = channelID;
		this.ageRating = ageRating;
		this.start = start;
		this.end = end;
		this.duration = Duration.between(start, end);
	}

}
