package com.github.tea_pack.sapphire.statistics.filters;

public class AgeFilter implements Filter<Integer>{
	public final int lower;
	public final int upper;

	public AgeFilter(int lower, int upper) {
		this.lower = lower;
		this.upper = upper;
	}

	@Override
	public boolean test(Integer subject) {
		return lower <= subject && subject < upper;
	}
}
