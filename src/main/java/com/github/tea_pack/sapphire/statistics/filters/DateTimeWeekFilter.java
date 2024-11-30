package com.github.tea_pack.sapphire.statistics.filters;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DateTimeWeekFilter implements Filter<LocalDateTime> {
	public final Filter<LocalDate> date;
	public final Filter<LocalTime> time;
	public final Filter<DayOfWeek> week;

	public DateTimeWeekFilter(DateFilter date, TimeFilter time, DayOfWeekFilter week) {
		this.date = date != null ? date : new MockFilter<>();
		this.time = time != null ? time : new MockFilter<>();
		this.week = week != null ? week : new MockFilter<>();
	}

	@Override
	public boolean test(LocalDateTime subject) {
		return date.test(subject.toLocalDate()) &&
				time.test(subject.toLocalTime()) &&
				week.test(subject.getDayOfWeek());
	}
}
