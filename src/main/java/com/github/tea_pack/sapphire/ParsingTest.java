package com.github.tea_pack.sapphire;

import com.github.tea_pack.sapphire.entities.Client;
import com.github.tea_pack.sapphire.parsers.CSVParser;
import com.github.tea_pack.sapphire.parsers.ClientParser;

import java.util.List;

public class ParsingTest {
	public static void main(String[] args) {
		String data = """
				client;address;gender;age
				u011766;улица Артамонова, 16;m;60-69
				u008046;улица Карла Маркса, 98;M;60-69
				u008053;улица 45 Стрелковой Дивизии, 285;M;40-49
				u003790;улица Юлюса Янониса, 5;Ж;40-49
				u006932;улица Беговая, 219/3;M;40-49
				u005115;улица Беговая, 168;Ж;30-39
				u013376;улица Берег реки Дон, 29-е;M;30-39
				u013854;улица Острогожская, 170/4;Ж;30-39
				u006951;проспект Патриотов, 38-а;Ж;40-49
				""";
		CSVParser csvParser = new CSVParser(data);
		try {
			csvParser.next();
			List<Client> list = ClientParser.parse(csvParser.consume());
			for (Client client : list) {
				System.out.printf("%d; gender: %s, %d-%d years; address: \"%s\"%n", client.ID, client.gender.toString(), client.age.start, client.age.end, client.address);
			}
		}
		catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}
}
