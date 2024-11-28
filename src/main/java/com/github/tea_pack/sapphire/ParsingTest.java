package com.github.tea_pack.sapphire;

import com.github.tea_pack.sapphire.entities.Channel;
import com.github.tea_pack.sapphire.entities.Client;
import com.github.tea_pack.sapphire.parsers.CSVParser;
import com.github.tea_pack.sapphire.parsers.ChannelParser;
import com.github.tea_pack.sapphire.parsers.ClientParser;

import java.util.List;

public class ParsingTest {
	public static void main(String[] args) {
		//clientTest();
		channelTest();
	}

	public static void channelTest() {
		String data = """
				pack_name;ch_id
				Спорт+ HD;192
				Спорт+ HD;119
				КХЛ HD;119
				Спорт 1 HD;192
				Матч Премьер;171
				Матч Премьер;141
				Viasat;235
				Viasat;211
				Viasat;170
				Viasat;205
				Viasat;189
				Viasat;153
				Viasat;191
				Viasat;168
				Дождь;248
				Матч! Футбол;103
				Матч! Футбол;136
				Матч! Футбол;174
				Unicast;167
				TV Бизнес Спорт;105
				TV Бизнес Спорт;261
				TV Бизнес Спорт;207
				TV Бизнес Спорт;192
				TV Бизнес Спорт;171
				TV Бизнес Спорт;103
				TV Бизнес Спорт;136
				TV Бизнес Спорт;174
				TV Бизнес Спорт;119
				""";
		CSVParser csvParser = new CSVParser(data);
		try {
			csvParser.next();
			List<Channel> list = ChannelParser.parse(csvParser.consume());
			for (Channel channel : list) {
				System.out.printf("%d in pack %s%n", channel.ID, channel.pack);
			}
		}
		catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}

	public static void clientTest() {
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
