package com.github.tea_pack.sapphire.entities;

import java.security.InvalidParameterException;

public class Range {
	public int start;
	public int end;

	public Range(int start, int end) {
		this.start = start;
		this.end = end;
		validate();
	}

	public static Range from(String from) {
		String[] range = from.split("-");
		int start = Integer.parseInt(range[0]);
		int end = Integer.parseInt(range[1]);
		return new Range(start, end);
	}

	public int disperse() {
		return end-start;
	}

	public void validate() {
		if(start > end){
			throw new InvalidParameterException("Invalid range: start > end");
		}
	}
}
