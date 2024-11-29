package com.github.tea_pack.sapphire;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import com.github.tea_pack.sapphire.entities.Client;
import com.github.tea_pack.sapphire.entities.View;
import com.github.tea_pack.sapphire.parsers.CSVParser;
import com.github.tea_pack.sapphire.parsers.ClientParser;
import com.github.tea_pack.sapphire.parsers.ViewParser;

public class ParserTests {

	public static void main(String[] args) {
		specTest();

	}

	public static void specTest() {
		Path path = Path.of("./src/test/java/com/github/tea_pack/sapphire/source_data/epg_stat_2024_10.csv");
		try (BufferedReader reader = Files.newBufferedReader(path)) {
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.contains("Союзмультфильма")){
					System.out.println(line);
				}
			}
		}
		catch (Exception ex) {

		}
	}

	// @Test
	public static void viewTest() {
		String data = """
				client;device;time_ch;ch_id;epg_name;time_epg;time_to_epg;duration;category;subcategory
				u011766;d008493;2024-10-01 05:35:38;216;"Самые шокирующие; гипотезы (16+)";2024-10-01 05:00:00;2024-10-01 06:00:00;1462;Познавательное;Расследование
				u002390;d016566;2024-10-01 19:05:14;104;Скорая помощь-2 1-я серия (16+);2024-10-01 00:55:00;2024-10-01 01:40:00;2700;Сериал;Драма
				""";
		String[] values = new String[]{};
		try {
			Path path = Path.of("./src/test/java/com/github/tea_pack/sapphire/source_data/epg_stat_2024_10.csv");
			CSVParser csvParser = new CSVParser(path);
			csvParser.next();

			int count = 0;
			while ((values = csvParser.next()) != null) {
				if (values.length > 10) {
					continue;
				}
				View view = ViewParser.parse(values);
				count++;
				// System.out.println(view.broadcast.name);
				// List<View> list = ViewParser.parse(csvParser.consume());
				// for(View view: list) {
				// System.out.println(count);
				// System.out.printf("BC: %s -- %s : %ss%n",
				// view.broadcast.start.format(ViewParser.DATE_TIME_FORMAT),
				// view.broadcast.end.format(ViewParser.DATE_TIME_FORMAT),
				// view.broadcast.duration.toSeconds());
				// System.out.printf("CL: %s -- %s : %ss%n%n",
				// view.start.format(ViewParser.DATE_TIME_FORMAT),
				// view.end.format(ViewParser.DATE_TIME_FORMAT), view.duration.toSeconds());
				// }
			}
			System.out.println(count);
		}
		catch (Exception ex) {
			ex.printStackTrace();
			for (int i = 0; i < values.length; i++) {
				System.out.printf("%d \"%s\"%n", i, values[i]);
			}
		}
	}

	// @Test
	public void clientTest() {
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

			}
		}
		catch (Exception ex) {
			System.err.println(ex.getMessage());
		}
	}
}
