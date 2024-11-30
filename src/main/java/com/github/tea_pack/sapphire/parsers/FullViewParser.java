package com.github.tea_pack.sapphire.parsers;

import com.github.tea_pack.sapphire.entities.*;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FullViewParser {
	public static List<FullView> parse(List<Client> clients, List<View> views, List<Channel> channels) {
		Map<Long, Client> clientMap = new HashMap<>();
		for(Client client: clients) {
			clientMap.put(client.clientID, client);
		}

		Map<Long, Channel> channelMap = new HashMap<>();
		for(Channel channel: channels){
			channelMap.put(channel.channelId, channel);
		}

		List<FullView> fullViews = new ArrayList<>();
		for(View view: views){
			Client client = clientMap.get(view.clientID);
			Channel channel = channelMap.get(view.broadcast.channelID);
			Broadcast broadcast = view.broadcast;
			ViewInfo viewInfo = new ViewInfo(view.deviceID, view.start, view.end, view.duration);
			FullView fullView = new FullView(client, channel, broadcast, viewInfo);
			fullViews.add(fullView);
		}

		return fullViews;
	}
}
