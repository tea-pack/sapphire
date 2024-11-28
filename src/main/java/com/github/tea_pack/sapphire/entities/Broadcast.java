package com.github.tea_pack.sapphire.entities;

import java.time.Duration;
import java.util.Calendar;

public class Broadcast {
    public int entityID;
	public final String name;
	public final int ageRating;
	public final Calendar start;
	public final Calendar end;
	public final Duration duration;

	public Broadcast(String name, int ageRating, Calendar start, Calendar end) {
		this.name = name;
		this.ageRating = ageRating;
		this.start = start;
		this.end = end;
		this.duration = duration();
	}

	private Duration duration() {
		return Duration.between(start.toInstant(), end.toInstant());
	}
}
