package com.github.tea_pack.sapphire.statistics.filters;

import java.time.Duration;

public class DurationFilter implements Filter<Duration> {
	public final Duration lower;
	public final Duration upper;

	public DurationFilter(Duration lower, Duration upper) {
		this.lower = lower;
		this.upper = upper;
	}

	@Override
	public boolean test(Duration subject) {
		return lower.compareTo(subject) <= 0 && upper.compareTo(subject) > 0;
	}
}
