package com.github.tea_pack.sapphire.statistics.filters;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class DayOfWeekFilter implements Filter<DayOfWeek>{
	// Days of week
	public static final int MONDAY = 0b1;
	public static final int TUESDAY = 0b10;
	public static final int WEDNESDAY = 0b100;
	public static final int THURSDAY = 0b1000;
	public static final int FRIDAY = 0b10000;
	public static final int SATURDAY = 0b100000;
	public static final int SUNDAY = 0b1000000;

	// Other
	public static final int WEEK = MONDAY | TUESDAY | WEDNESDAY | THURSDAY | FRIDAY | SATURDAY | SUNDAY;
	public static final int NONE = 0b0;
	public static final int WORKWEEK = MONDAY | TUESDAY | WEDNESDAY | THURSDAY | FRIDAY;
	public static final int WEEKEND = SATURDAY | SUNDAY;

	// Field
	public final int mask;

	public DayOfWeekFilter(int mask) {
		this.mask = mask & WEEK;
	}

	// Checkers
	public boolean isMonday() {
		return (mask & MONDAY) != 0;
	}

	public boolean isTuesday() {
		return (mask & TUESDAY) != 0;
	}

	public boolean isWednesday() {
		return (mask & WEDNESDAY) != 0;
	}

	public boolean isThursday() {
		return (mask & THURSDAY) != 0;
	}

	public boolean isFriday() {
		return (mask & FRIDAY) != 0;
	}

	public boolean isSaturday() {
		return (mask & SATURDAY) != 0;
	}

	public boolean isSunday() {
		return (mask & SUNDAY) != 0;
	}

	public boolean isWeek() {
		return (mask & WEEK) != 0;
	}

	public boolean isMask(int mask) {
		return (this.mask & mask) != 0;
	}

	// Debug
	private static String[] DAYS = new String[]{
			"Monday",
			"Tuesday",
			"Wednesday",
			"Thursday",
			"Friday",
			"Saturday",
			"Sunday",
	};

	@Override
	public String toString() {
		if ((mask & WEEK) == 0) {
			return "";
		}
		List<String> list = new ArrayList<>();
		for (int i = 0; i < 7; i++) {
			if (mask >> i != 0) {
				list.add(DAYS[i]);
			}
		}
		String result = "";
		int last = list.size() -1;
		for (int i = 0; i <= last; i++) {
			result += list.get(i) + (i == last? "": ", ");
		}
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		DayOfWeekFilter that = (DayOfWeekFilter) o;

		return mask == that.mask;
	}

	@Override
	public int hashCode() {
		return mask;
	}

	@Override
	public boolean test(DayOfWeek subject) {
		return switch (subject.getValue()) {
			case 1 -> isMonday();
			case 2 -> isTuesday();
			case 3 -> isWednesday();
			case 4 -> isThursday();
			case 5 -> isFriday();
			case 6 -> isSaturday();
			case 7 -> isSunday();
			default -> false;
		};
	}
}
