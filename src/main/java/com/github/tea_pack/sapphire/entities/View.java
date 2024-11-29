package com.github.tea_pack.sapphire.entities;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class View {
	public int clientID;
	public int deviceID;
	public LocalDateTime start;
	public LocalDateTime end;
	public Duration duration;
	public Broadcast broadcast;

	public View(int clientID, int deviceID, LocalDateTime start, Duration duration, Broadcast broadcast) {
		this.clientID = clientID;
		this.deviceID = deviceID;
		this.start = start;
		this.end = start.plus(duration);
		this.duration = duration;
		this.broadcast = broadcast;
	}
}
