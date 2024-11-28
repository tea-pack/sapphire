package com.github.tea_pack.sapphire.parsers;

import com.github.tea_pack.sapphire.entities.View;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ViewParser {
	// TODO: move this constant
	public static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	public static View parse(String[] values) throws Exception{
		int clientID = Integer.parseInt(values[0].substring(1));
		int deviceID = Integer.parseInt(values[1].substring(1));
		LocalDateTime startTime = LocalDateTime.parse(values[2], DATE_TIME_FORMAT);

		return new View(clientID, deviceID, startTime ); // TODO: finish parsing
	}

	public static List<View> parse(List<String[]> values) {

	}
}
