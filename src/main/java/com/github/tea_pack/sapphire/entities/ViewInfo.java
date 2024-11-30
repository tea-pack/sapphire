package com.github.tea_pack.sapphire.entities;

import java.time.Duration;
import java.time.LocalDateTime;

public class ViewInfo {
	public final long deviceId;
	public final LocalDateTime start;
	public final LocalDateTime end;
	public final Duration duration;

	public ViewInfo(long deviceId, LocalDateTime start, LocalDateTime end, Duration duration) {
		this.deviceId = deviceId;
		this.start = start;
		this.end = end;
		this.duration = duration;
	}
}
