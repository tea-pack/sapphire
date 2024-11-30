package com.github.tea_pack.sapphire.statistics.filters;

import com.github.tea_pack.sapphire.entities.Gender;

public class GenderFilter implements Filter<Gender> {
	public final Gender allowed;

	public GenderFilter(Gender allowed) {
		this.allowed = allowed;
	}

	@Override
	public boolean test(Gender subject) {
		return subject == allowed;
	}
}


