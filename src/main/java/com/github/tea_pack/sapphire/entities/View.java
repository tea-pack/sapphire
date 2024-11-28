package com.github.tea_pack.sapphire.entities;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class View {
	public int clientID;
	public int deviceID;
	public LocalDateTime startTime;
	public Duration watchDuration;
	public Broadcast broadcast;
	public String category;
	public List<String> genres;

	public View(int clientID, int deviceID, LocalDateTime startTime, Duration watchDuration, Broadcast broadcast, String category, List<String> genres) {
		this.clientID = clientID;
		this.deviceID = deviceID;
		this.startTime = startTime;
		this.watchDuration = watchDuration;
		this.broadcast = broadcast;
		this.category = category;
		this.genres = genres;
	}
}
