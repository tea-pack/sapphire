package com.github.tea_pack.sapphire.statistics.filters;

import com.github.tea_pack.sapphire.entities.Broadcast;

import java.time.Duration;
import java.time.LocalDateTime;

public class BroadcastFilter implements Filter<Broadcast> {

	public final Filter<Duration> dur;
	public final Filter<String> name;
	public final Filter<LocalDateTime> dtw;

	public BroadcastFilter(Filter<Duration> dur, Filter<String> name, Filter<LocalDateTime> dtw) {
		this.dur = dur != null ? dur : new MockFilter<>();
		this.name = name != null ? name : new MockFilter<>();
		this.dtw = dtw != null ? dtw : new MockFilter<>();
	}

	@Override
	public boolean test(Broadcast broadcast) {
		return dur.test(broadcast.duration) && name.test(broadcast.name) && dtw.test(broadcast.start);
	}
}
