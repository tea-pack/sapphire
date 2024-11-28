package com.github.tea_pack.sapphire.parsers;

import com.github.tea_pack.sapphire.entities.Client;
import com.github.tea_pack.sapphire.entities.Gender;
import com.github.tea_pack.sapphire.entities.Range;

import java.util.Locale;

public class ClientParser {
	public static Client parse(String line) {
		String[] split = line.split(";");
		int ID = Integer.parseInt(split[0].substring(1));
		String address = split[1];
		Gender gender = switch (split[2].toUpperCase(Locale.of("ru"))){
			case "М" -> Gender.MALE;
			case "Ж" -> Gender.FEMALE;
			default -> Gender.UNKNOWN;
		};
		Range age = Range.from(split[3]);
		return new Client(ID, address, gender, age);
	}
}
