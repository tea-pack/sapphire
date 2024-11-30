package com.github.tea_pack.sapphire.statistics;

import com.github.tea_pack.sapphire.entities.Broadcast;
import com.github.tea_pack.sapphire.entities.FullView;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BroadcastStatistics {
	public final Broadcast broadcast;
	public final HashMap<Long, Duration> clientWatch;
	public final HashMap<Long, List<FullView>> clientView;

	public BroadcastStatistics(Broadcast broadcast) {
		this.broadcast = broadcast;
		clientWatch = new HashMap<>();
		clientView = new HashMap<>();
	}

	public void view(FullView view) {
		if (view.info.duration.toSeconds() > 10) {
			if (broadcast.equals(view.broadcast)) {
				clientWatch.compute(view.client.clientID, (client, duration) ->
						duration == null ? Duration.ZERO.plus(view.info.duration) : duration.plus(view.info.duration));
				clientView.compute(view.client.clientID, (client, list) -> {
					if(list == null) {
						list = new ArrayList<>();
					}
					list.add(view);
					return list;
				});
			}
		}
	}

	public Duration totalWatchTime() {
		Duration time = Duration.ZERO;
		for (Duration clientWatchTime: clientWatch.values()) {
			time = time.plus(clientWatchTime);
		}
		return time;
	}

	public Duration averageWatchTime() {
		return totalWatchTime().dividedBy(clientWatch.size());
	}

	public long uniqueWatchers() {
		return clientWatch.size();
	}


	public static Map<Broadcast, BroadcastStatistics> getBroadcastStatistics(List<FullView> views) {
		Map<Broadcast, BroadcastStatistics> map = new HashMap<>();
		for (FullView view : views) {
			if(!map.containsKey(view.broadcast)){
				map.put(view.broadcast, new BroadcastStatistics(view.broadcast));
			}
			map.get(view.broadcast).view(view);
		}
		return map;
	}
}
