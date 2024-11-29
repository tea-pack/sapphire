package com.github.tea_pack.sapphire.parsers;

import java.util.ArrayList;
import java.util.List;

import com.github.tea_pack.sapphire.entities.Channel;

public class ChannelParser {
	public static Channel parse(String[] values) {
		int ID = Integer.parseInt(values[1]);
		String pack = values[0];
		return new Channel(ID, pack);
	}

	public static List<Channel> parse(List<String[]> values) {
		List<Channel> channels = new ArrayList<>();
		for(String[] val: values){
			channels.add(ChannelParser.parse(val));
		}
		return channels;
	}
}
