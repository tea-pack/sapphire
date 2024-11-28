package com.github.tea_pack.sapphire.parsers;

import com.github.tea_pack.sapphire.entities.Client;
import com.github.tea_pack.sapphire.entities.Gender;
import com.github.tea_pack.sapphire.entities.Range;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ClientParser {
	public static Client parse(String[] values) {
		int ID = Integer.parseInt(values[0].substring(1));
		String address = values[1];
		Gender gender = switch (values[2].toUpperCase(Locale.of("ru"))){
			case "М", "M" -> Gender.MALE;
			case "Ж", "W", "F" -> Gender.FEMALE;
			default -> Gender.UNKNOWN;
		};
		Range age = Range.from(values[3]);
		return new Client(ID, address, gender, age);
	}

	public static List<Client> parse(List<String[]> values) {
		List<Client> clients = new ArrayList<>();
		for(String[] val: values) {
			clients.add(parse(val));
		}
		return clients;
	}
}
