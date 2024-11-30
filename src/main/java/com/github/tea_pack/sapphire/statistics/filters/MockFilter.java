package com.github.tea_pack.sapphire.statistics.filters;

public class MockFilter<T> implements Filter<T>{
	@Override
	public boolean test(T subject) {
		return true;
	}
}
