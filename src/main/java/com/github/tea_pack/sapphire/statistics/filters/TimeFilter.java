package com.github.tea_pack.sapphire.statistics.filters;

import java.time.LocalTime;

public class TimeFilter implements Filter<LocalTime> {
	public final LocalTime start;
	public final LocalTime end;

	public TimeFilter(LocalTime start, LocalTime end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public boolean test(LocalTime subject) {
		return start.isBefore(subject) && end.isAfter(subject);
	}
}
