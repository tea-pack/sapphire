package com.github.tea_pack.sapphire.statistics.filters;

import java.time.LocalDate;

public class DateFilter implements Filter<LocalDate>{
	public final LocalDate start;
	public final LocalDate end;

	public DateFilter(LocalDate start, LocalDate end) {
		this.start = start;
		this.end = end;
	}

	@Override
	public boolean test(LocalDate check) {
		return start.isBefore(check) && end.isAfter(check);
	}
}
