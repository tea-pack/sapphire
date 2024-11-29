package com.github.tea_pack.sapphire.utility;

import java.time.Duration;
import java.time.format.DateTimeFormatter;

public class FMT {
	public static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	public static String formatDuration(Duration duration) {
		long s = duration.toSeconds();
		return String.format("%d:%02d:%02d", s / 3600, (s % 3600) / 60, (s % 60));
	}
}
