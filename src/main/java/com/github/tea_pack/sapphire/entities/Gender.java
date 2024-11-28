package com.github.tea_pack.sapphire.entities;

public enum Gender {
	MALE,
	FEMALE,
	UNKNOWN,
	;

	@Override
	public String toString() {
		return switch (this) {
			case MALE -> "Мужской";
			case FEMALE -> "Женский";
			case UNKNOWN -> "Неизвестно";
		};
	}
}
