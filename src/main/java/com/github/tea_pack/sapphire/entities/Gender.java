package com.github.tea_pack.sapphire.entities;

import java.util.Locale;

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

	public static Gender of(String literal) {
		return switch (literal.toUpperCase(Locale.of("ru"))){
			case "М", "M" -> Gender.MALE;
			case "Ж", "W", "F" -> Gender.FEMALE;
			default -> Gender.UNKNOWN;
		};
	}
}
