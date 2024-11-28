package com.github.tea_pack.sapphire.parsers;

import com.github.tea_pack.sapphire.entities.View;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class ViewParser {
	public static View parse(String[] values) throws Exception{
		int clientID = Integer.parseInt(values[0].substring(1));
		int deviceID = Integer.parseInt(values[1].substring(1));
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar startTime = new GregorianCalendar(format.parse(values[2]));
	}

	public static List<View> parse(List<String[]> values) {

	}
}
